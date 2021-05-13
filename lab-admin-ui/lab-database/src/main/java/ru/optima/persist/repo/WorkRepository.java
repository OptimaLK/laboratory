package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    Work findById(long id);
}
