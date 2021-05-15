package ru.optima.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.BagRepository;
import ru.optima.persist.repo.EquipmentRepository;
import ru.optima.persist.repo.UserRepository;
import ru.optima.repr.BagRepr;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BagServiceImpl implements BagService{

    private BagRepository bagRepository;
    private UserRepository userRepository;
    private EquipmentRepository equipmentRepository;

    public BagServiceImpl(BagRepository bagRepository, UserRepository userRepository, EquipmentRepository equipmentRepository) {
        this.bagRepository = bagRepository;
        this.userRepository = userRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public void addEquipmentToBag(Equipment equipment, User user) {
        User u = userRepository.findUserById(user.getId()).get();
        List<Bag> bags = u.getBags();
        if (bags.size() != 0){
            Bag lastBag = bags.get(bags.size() - 1);
            for (int i = 0; i < lastBag.getEquipments().size(); i++) {
                if (lastBag.getEquipments().get(i).equals(equipment)){
                    return;
                }
            }
        } else {
            bags.add(new Bag(user));
        }
        bags.get(bags.size() - 1).getEquipments().add(equipment);
        bagRepository.save(bags.get(bags.size() - 1));
    }

    @Override
    public void deleteEquipmentToBag(Equipment equipment, User user) {
        User u = userRepository.findUserById(user.getId()).get();
        List<Bag> bags = u.getBags();
        Bag lastBag = bags.get(u.getBags().size() - 1);
        for (int i = 0; i < lastBag.getEquipments().size(); i++) {
            if (lastBag.getEquipments().get(i).equals(equipment)){
                lastBag.getEquipments().remove(i);
            }
        }
        bagRepository.save(lastBag);
    }

    @Override
    public void presenceInBag(Equipment equipment) {
        if(equipment.getTaken() == null || equipment.getTaken()) {
            equipment.setTaken(false);
        } else {
            equipment.setTaken(true);
        }
    }

//    @Override
//    public void delEquipmentToBag(Equipment equipment, User user) {
//        User u = userRepository.findUserById(user.getId()).get();
//        Bag bag = u.getBag();
//        if (bag == null){
//            bag = new Bag(user);
//        }
//        for (int i = 0; i < bag.getEquipments().size(); i++) {
//            if (bag.getEquipments().get(i).equals(equipment)){
//                break;
//            }
//        }
//        bag.getEquipments().remove(equipment);
//        equipment.setActivity(true);
//        equipmentRepository.save(equipment);
//        bagRepository.save(bag);
//    }

    @Override
    public List<BagRepr> findAll() {
        return bagRepository.findAll().stream()
                .map(BagRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Equipment findByEquipmentId(Long equipmentId) {
        return equipmentRepository.findById(equipmentId).get();
    }

}
