package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.equipments.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository <Commentary, Long> {
}
