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
    private EquipmentRepository equipmentRepository;

    public BagServiceImpl(BagRepository bagRepository, EquipmentRepository equipmentRepository) {
        this.bagRepository = bagRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public void addEquipmentToBag(Equipment equipment, User user) {
        List<Bag> bags = user.getBags();
        if (bags.size() == 0){
            bags.add(new Bag(user));
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
    public List<BagRepr> findAll() {
        return bagRepository.findAll().stream()
                .map(BagRepr::new)
                .collect(Collectors.toList());
    }
}
