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
    List<Work> findAllWorksByUserId(Long id);

    @Query( "select w from Work w join w.users u where w.status = 0 and u.id = ?1")
    List<Work> findAllNewWorksByUserId(Long id);

    @Query( "select w from Work w join w.users u where w.status < 3 and u.id = ?1")
    List<Work> findAllActualWorksByUserId(Long id);

    @Query( "select w from Work w join w.users u where w.status = 2 and u.id = ?1")
    List<Work> findAllOnCheckWorksByUserId(Long id);

    @Query( "select w from Work w join w.users u where w.status = 1 and u.id = ?1")
    List<Work> findAllInWorkWorksByUserId(Long id);

    @Query( "select w from Work w join w.users u where w.status = 3 and u.id = ?1")
    List<Work> findAllInArchiveWorksByUserId (Long id);
}
