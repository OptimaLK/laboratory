package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.BagRepr;
import ru.optima.repr.UserRepr;

import java.util.List;

@Service
public interface BagService {

    void addEquipmentToBag(Equipment equipment, User user);

    List<BagRepr> findAll();

    void deleteEquipmentToBag(Equipment equipment, User user);

    List<Equipment> findAllEquipments(User user);
    List<Equipment> findAllEquipmentsFirstBag(User user);

    void deleteAllEquipmentsInBag(User user);

    Bag createBagReprByBag(Bag bag);

    void createNewBagAndSaveOldBag(BagRepr bagRepr);

    BagRepr createBagReprAndAddUserAndEquipments(User user, List<Equipment> equipments);
}
