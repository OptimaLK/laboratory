package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.BagRepr;
import ru.optima.repr.UserRepr;

import java.util.List;
import java.util.Optional;

@Service
public interface BagService {

    void addEquipmentToBag(Equipment equipment, User user);

    List<BagRepr> findAll();

    Equipment findByEquipmentId(Long equipmentId);

    Bag addEquipmentToBagByUser(User user);

    void deleteEquipmentToBag(Equipment equipment, User user);
}
