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
import ru.optima.repr.EquipmentRepr;

import java.util.ArrayList;
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
        Bag bag = u.getBag();
        if (bag == null){
            bag = new Bag(user);
        }
        for (int i = 0; i < u.getBag().getEquipments().size(); i++) {
            if (u.getBag().getEquipments().get(i).equals(equipment)){
                return;
            }
        }
        bag.getEquipments().add(equipment);
        equipment.setActivity(false);
        bagRepository.save(bag);
    }

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

    @Override
    public Bag addEquipmentToBagByUser(User user) {
        return bagRepository.findByUser(user);
    }

    @Override
    public void deleteEquipmentToBag(Equipment equipment, User user) {
        User u = userRepository.findUserById(user.getId()).get();
        Bag bag = u.getBag();
        if (bag == null){
            bag = new Bag(user);
        }
        for (int i = 0; i < u.getBag().getEquipments().size(); i++) {
            if (bag.getEquipments().get(i).equals(equipment)){
                bag.getEquipments().remove(i);
            }
        }
        equipment.setActivity(true);
        bagRepository.save(bag);
    }


}
