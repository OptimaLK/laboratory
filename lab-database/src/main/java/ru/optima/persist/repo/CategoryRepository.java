package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.optima.persist.model.equipments.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
