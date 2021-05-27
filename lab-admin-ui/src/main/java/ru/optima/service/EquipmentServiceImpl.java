package ru.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.repr.EquipmentRepr;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.EquipmentRepository;
import ru.optima.warning.NotFoundException;

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

    public void save(EquipmentRepr equipmentRepr) {
        Equipment equipment = new Equipment();
        equipment.setId(equipmentRepr.getId());
        equipment.setName(equipmentRepr.getName());
        equipment.setFactoryNumber(equipmentRepr.getFactoryNumber());
        equipment.setInventoryNumber(equipmentRepr.getInventoryNumber());
        equipment.setVerificationDate(equipmentRepr.getVerificationDate());
        equipment.setVerificationDateEnd(equipmentRepr.getVerificationDateEnd());
        equipment.setVerificationNumber(equipmentRepr.getVerificationNumber());
        equipmentRepository.save(equipment);
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

    @Override
    public Equipment findByEId(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> findAllByCategoryId(Long id){
        return equipmentRepository.findAllByCategoryId(id);
    }

}
