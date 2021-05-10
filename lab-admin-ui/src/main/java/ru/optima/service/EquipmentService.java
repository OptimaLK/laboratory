package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.EquipmentRepr;

import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentService {

    void save(EquipmentRepr equipmentRepr);

    List<EquipmentRepr> findAll();

    Optional<EquipmentRepr> findById(Long id);
    Equipment findByEId(Long id);
    Equipment findByName(String name);

    void delete(Long id);
}
