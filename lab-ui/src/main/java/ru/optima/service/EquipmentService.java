package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.controllers.repr.EquipmentRepr;

import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentService {

    List<EquipmentRepr> findAll();

    Optional<EquipmentRepr> findById(Long id);

}
