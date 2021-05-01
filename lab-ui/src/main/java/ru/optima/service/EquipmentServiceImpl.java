package ru.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.controllers.repr.EquipmentRepr;
import ru.optima.persist.repo.EquipmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    public void setUserRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    @Override
    public List<EquipmentRepr> findAll() {
        return equipmentRepository.findAll().stream()
                .map(EquipmentRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EquipmentRepr> findById(Long id) {
        return equipmentRepository.findById(id).map(EquipmentRepr::new);
    }



}
