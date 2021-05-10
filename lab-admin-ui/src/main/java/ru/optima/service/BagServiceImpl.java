package ru.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.BagRepository;
import ru.optima.persist.repo.EquipmentRepository;
import ru.optima.persist.repo.UserRepository;
import ru.optima.repr.BagRepr;
import ru.optima.repr.UserRepr;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BagServiceImpl implements BagService{

    private BagRepository bagRepository;
    private UserRepository userRepository;

    public BagServiceImpl(BagRepository bagRepository, UserRepository userRepository) {
        this.bagRepository = bagRepository;
        this.userRepository = userRepository;
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

}
