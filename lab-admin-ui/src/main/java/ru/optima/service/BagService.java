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

    void deleteBagById(User user, Long bagId);

    Bag createBagReprByBag(Bag bag);

    void createNewBagAndSaveOldBag(BagRepr bagRepr, User user);

    void addBag(User user);

    BagRepr createBagReprAndAddUserAndEquipments(User user, List<Equipment> equipments);

    List<Equipment> selectBag(Long id, User user);

}
