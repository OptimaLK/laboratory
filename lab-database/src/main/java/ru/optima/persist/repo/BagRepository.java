package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.equipments.Bag;

import java.util.List;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
    Bag findById(long id);

}
