package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.User;
import ru.optima.persist.model.Work;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findById(long id);

    @Query( "select w from Work w join w.users u where u.id = ?1")
    List<Work> findAllWorksByUserId(Long id);

    @Query( "select distinct w from Work w join w.users u where w.workStatus.name = ?2 and u.id = ?1 order by w.registrationDate desc")
    List<Work> findAllWorksByUserIdWithStatusName(Long id, String statusName);

    @Query( "select distinct w from Work w join w.users u where w.workStatus.name = ?3 OR w.workStatus.name = ?2 and u.id = ?1 order by w.registrationDate desc")
    List<Work> findAllWorksByUserIdWithStatusName(Long id, String statusNameOne, String statusNameTwo);
}
