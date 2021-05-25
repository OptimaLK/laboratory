package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.WorkStatus;

@Repository
public interface WorkStatusRepository extends JpaRepository<WorkStatus, Long> {

    @Query("select ws from WorkStatus ws where ws.name = ?1")
    WorkStatus findByName (String name);
}
