package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findById(long id);

}
