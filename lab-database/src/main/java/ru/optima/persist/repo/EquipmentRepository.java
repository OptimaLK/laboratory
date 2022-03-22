package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.equipments.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findById(long id);
    List<Equipment> findAllByCategoryId(long id);
    List<Equipment> findAllByCategoryIdAndTaken(Long categoryId, Boolean taken);
    @Modifying

    @Query("DELETE FROM Commentary c where  c.id = ?1")
    void deleteCommentary(Long id);
}
