package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.UserRepr;

@Service
public interface BagService {

    void addEquipmentToBag(Equipment equipment, User user);

}
