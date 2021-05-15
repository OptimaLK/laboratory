package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.Work;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findById(long id);

    @Query( "select w from Work w join w.users u where u.id = ?1")
    public List<Work> findAllWorksByUserId(Long id);
}
