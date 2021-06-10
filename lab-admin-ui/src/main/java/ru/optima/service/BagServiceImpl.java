package ru.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.persist.model.Protocol;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.BagRepository;
import ru.optima.persist.repo.ProtocolRepository;
import ru.optima.repr.BagRepr;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BagServiceImpl implements BagService{

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
        List<Bag> bags = user.getBags();
        if (bags.size() == 0){
            bag = new Bag();
            bag.setBag(user);
            bags.add(bag);
        }
        if (equipment.getTaken() == null || equipment.getTaken()) {
            equipment.setTaken(false);
            bags.get(bags.size() - 1).getEquipments().add(equipment);
            bagRepository.save(bags.get(bags.size() - 1));
        } else {
            equipment.setTaken(true);
        }
    }

    @Override
    public void deleteEquipmentToBag(Equipment equipment, User user) {
        equipment.setNameUserWhoTakenEquipment("");
        List<Bag> bags = user.getBags();
        if (user.getBags().size() != 0) {
            Bag lastBag = bags.get(user.getBags().size() - 1);
            for (int i = 0; i < lastBag.getEquipments().size(); i++) {
                if (lastBag.getEquipments().get(i).equals(equipment)){
                    lastBag.getEquipments().remove(i);
                    equipment.setTaken(true);
                    bagRepository.save(lastBag);
                    return;
                }
            }
        }
    }


    @Override
    public List<Equipment> findAllEquipments(User user) {
        if (user.getBags().size() == 0)
            return null;
        Bag lastBag = user.getBags().get(user.getBags().size() - 1);
        return lastBag.getEquipments();
    }

    @Override
    public List<Equipment> findAllEquipmentsFirstBag(User user) {
        if (user.getBags().size() == 0)
            return null;
        Bag firstBag = user.getBags().get(0);
        return firstBag.getEquipments();
    }

    @Override
    public void deleteAllEquipmentsInBag(User user) {
        if (user.getBags().size() == 0)
            return;
        Bag lastBag = user.getBags().get(user.getBags().size() - 1);
        for (int i = lastBag.getEquipments().size() - 1; i >= 0; i--) {
            lastBag.getEquipments().get(i).setTaken(true);
            lastBag.getEquipments().remove(i);
        }
        bagRepository.save(lastBag);
    }

    @Override
    public Bag createBagReprByBag(Bag bag) {
        return bag;
    }

    @Override
    public void createNewBagAndSaveOldBag(BagRepr bag) {
        List<Bag> bagList = bagRepository.findAll();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.HOUR, bag.getCountHourLifeTime());
        for(int i = 0; i < bagList.size() - 1; i++) {
            if(bagList.get(i).getWork() == null && bagList.get(i).getUser().getId().equals(bag.getUser().getId())) {
                if(this.bag == null) {
                    this.bag = new Bag();
                    this.bag.setBag(bagList.get(i).getUser());
                }
                this.bag.setId(bagList.get(i).getId());
                break;
            }
        }
//        this.bag.setId(bag.getId());
        this.bag.setName(bag.getName());
        this.bag.setBirthTime(new Date(System.currentTimeMillis()));
        this.bag.setLifeTime(new Timestamp(calendar.getTimeInMillis()));
        this.bag.setEquipments(bag.getEquipments());
        this.bag.setUser(bag.getUser());
        this.bag.setWork(bag.getWork());
        this.bag.setNumberProtocol(bag.getNumberProtocol());
        for (int i = 0; i < bag.getCountProtocol(); i++) {
            Protocol protocol = new Protocol();
            this.bag.getNumberProtocol().add(protocol);
        }
        bagRepository.save(this.bag);
    }

    @Override
    public BagRepr createBagReprAndAddUserAndEquipments(User user, List<Equipment> equipments) {
        BagRepr bagRepr = new BagRepr();
        bagRepr.setUser(user);
        bagRepr.setEquipments(equipments);
        return bagRepr;
    }


    @Override
    public List<BagRepr> findAll() {
        return bagRepository.findAll().stream()
                .map(BagRepr::new)
                .collect(Collectors.toList());
    }
}
