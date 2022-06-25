package ru.optima.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.beans.Dairy;
import ru.optima.persist.model.Protocol;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.BagRepository;
import ru.optima.persist.repo.ProtocolRepository;
import ru.optima.repr.BagRepr;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BagServiceImpl implements BagService {

    private BagRepository bagRepository;
    private ProtocolRepository protocolRepository;
    private Bag bag;

    public BagServiceImpl(BagRepository bagRepository, ProtocolRepository protocolRepository) {
        this.bagRepository = bagRepository;
        this.protocolRepository = protocolRepository;
    }

    @Override
    public void addEquipmentToBag(Equipment equipment, User user) {
        equipment.setNameUserWhoTakenEquipment(user.getLastName());
        List <Bag> bags = user.getBags();
        if(bags.size() != 0 && bag == null && bags.get(bags.size() - 1).getWork() == null)
            bag = bags.get(bags.size() - 1);
        else if(bag == null || !user.getId().equals(bag.getUser().getId()) && bags.size() == 0)
            bag = new Bag();
        else if(!user.getId().equals(bag.getUser().getId()))
            bag = bags.get(bags.size() - 1);
        if(bags.size() == 0 || bag.getUser() == null) {
            bag.setBag(user);
            bag.setUser(user);
            bag.setStatus(false);
            bags.add(bag);
        }
        if(equipment.getTaken() == null || equipment.getTaken()) {
            equipment.setTaken(false);
            bags.get(bags.size() - 1).getEquipments().add(equipment);
            bagRepository.save(bags.get(bags.size() - 1));
        } else {
            equipment.setTaken(true);
            equipment.setNameUserWhoTakenEquipment("");
        }
    }

    @Override
    public void deleteEquipmentToBag(Equipment equipment, User user) {
        List <Bag> bags = user.getBags();
        Bag lastBag = null;
        for(Bag value : bags) {
            for(Equipment equ : value.getEquipments()) {
                if(equ.getId().equals(equipment.getId())) {
                    lastBag = bagRepository.findById(value.getId()).orElse(new Bag());
                    break;
                }
            }
        }
        if(user.getBags().size() != 0) {
            assert lastBag != null;
            for(int i = 0; i < lastBag.getEquipments().size(); i++) {
                if(lastBag.getEquipments().get(i).equals(equipment)) {
                    lastBag.getEquipments().get(i).setTaken(true);
                    lastBag.getEquipments().get(i).setNameUserWhoTakenEquipment("");
                    lastBag.getEquipments().remove(i);
                    bagRepository.save(lastBag);
                    return;
                }
            }
        }
    }


    @Override
    public List <Equipment> findAllEquipments(User user) {
        if(user.getBags().size() == 0) return null;
        Bag lastBag = user.getBags().get(user.getBags().size() - 1);
        return lastBag.getEquipments();
    }

    @Override
    public List <Equipment> findAllEquipmentsFirstBag(User user) {
        if(user.getBags().size() == 0) return null;
        Bag firstBag = user.getBags().get(0);
        return firstBag.getEquipments();
    }

    @Override
    public void deleteAllEquipmentsInBag(User user) {
        if(user.getBags().size() == 0) return;
        Bag lastBag = user.getBags().get(user.getBags().size() - 1);
        for(int i = lastBag.getEquipments().size() - 1; i >= 0; i--) {
            lastBag.getEquipments().get(i).setTaken(true);
            lastBag.getEquipments().get(i).setNameUserWhoTakenEquipment("");
            lastBag.getEquipments().remove(i);
        }
        bagRepository.save(lastBag);
    }

    @Override
    public void deleteBagById(User user, Long bagId) {
        Bag bag = bagRepository.findById(bagId).orElse(new Bag());
        for(int i = bag.getEquipments().size() - 1; i >= 0; i--) {
            bag.setLifeTime(new Timestamp(System.currentTimeMillis()));
            bag.getEquipments().get(i).setTaken(true);
            bag.getEquipments().get(i).setNameUserWhoTakenEquipment("");
        }
        bag.setStatus(false);
        bagRepository.save(bag);
    }


    @Override
    public Bag createBagReprByBag(Bag bag) {
        return bag;
    }

    public List<Bag> findAllEquipmentsInBag(Dairy dairy) {
        List<Bag> bagsDay = new ArrayList<>();
        List<Bag> bags = bagRepository.findAll();
        Calendar cal = Calendar.getInstance();
        for (Bag bagAll : bags){
            if (bagAll.getBirthTime() != null){
                cal.set(Calendar.DAY_OF_MONTH, bagAll.getBirthTime().getDate());
                cal.set(Calendar.MONTH, bagAll.getBirthTime().getMonth());
                cal.set(Calendar.YEAR, bagAll.getBirthTime().getYear() + 1900);
                if (cal.get(Calendar.DAY_OF_MONTH) == dairy.todayDay()) {
                    bagsDay.add(bagAll);
                }
            }

        }
        return bagsDay;
    }

    @Override
    public void createNewBagAndSaveOldBag(BagRepr bag, User user) {
        List<Bag> bagList = bagRepository.findAll();
        List<User> users = new ArrayList <>();
        users.add(user);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.HOUR, bag.getCountHourLifeTime());
        for(Bag value : bagList) {
            if(value.getWork() == null) {
                if(this.bag == null) {
                    this.bag = new Bag();
                    this.bag.setUsers(users);
                    this.bag.setUser(user);
                    if(bag.getId() != null)
                        this.bag.setId(bag.getId());
                    }
                    if(value.getId().equals(bag.getId())) {
                        this.bag.setId(value.getId());
                        this.bag.setEquipments(value.getEquipments());
                    }
                }
            break;
        }
        if(this.bag == null) {
            List <Bag> bags = user.getBags();
            this.bag = bags.get(bags.size() - 1);
        }
        this.bag.setName(bag.getName());
        this.bag.setBirthTime(new Timestamp(System.currentTimeMillis()));
        this.bag.setLifeTime(new Timestamp(calendar.getTimeInMillis()));
        if(this.bag.getUsers() == null) {
            this.bag.setUsers(users);
            this.bag.setUser(user);
        }
        this.bag.setWork(bag.getWork());
        this.bag.setNumberProtocol(bag.getNumberProtocol());
        for(int i = 0; i < bag.getCountProtocol(); i++) {
            Protocol protocol = new Protocol();
            this.bag.getNumberProtocol().add(protocol);
        }
        this.bag.setStatus(true);
        bagRepository.save(this.bag);
        this.bag = new Bag();
    }

    @Override
    public void addBag(User user) {
        List <Bag> bags = user.getBags();
        if(bags.size() == 0 || bag.getUser() == null) {
            bag.setBag(user);
            bag.setUser(user);
            bags.add(bag);
        }
            bagRepository.save(bags.get(bags.size() - 1));
    }

    @Override
    public BagRepr createBagReprAndAddUserAndEquipments(User user, List<Equipment> equipments) {
        List<User> users = new ArrayList <>();
        users.add(user);
        BagRepr bagRepr = new BagRepr();
        bagRepr.setId(user.getBags().get(user.getBags().size() - 1).getId());
        bagRepr.setUsers(users);
        bagRepr.setUser(user);
        bagRepr.setEquipments(equipments);
        return bagRepr;
    }

    @Override
    public List <Equipment> selectBag(Long bagId, User user) {
        if(user.getBags().size() == 0) return null;
        Bag selBag = user.getBags().get(0);
        for(Bag value : user.getBags()) {
            if(value.getId().equals(bagId))
                selBag = value;
        }
        return selBag.getEquipments();
    }


    @Override
    public List <BagRepr> findAll() {
        List<BagRepr> bagReprList = bagRepository.findAll().stream().map(BagRepr :: new).collect(Collectors.toList());
        for (int i = bagReprList.size() - 1; i > -1; i--) {
            if(bagReprList.get(i).getBirthTime() == null){
                bagReprList.remove(i);
            }
        }
        return bagReprList;
    }


}
