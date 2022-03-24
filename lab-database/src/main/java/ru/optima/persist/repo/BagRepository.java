package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;

import java.util.List;
import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
    Bag findById(long id);

    Optional<Bag> findBagById(Long id);

    Bag findBagByEquipments(Equipment equipment);
}
