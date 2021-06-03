package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.Protocol;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long> {
    Protocol findById(long id);

}
