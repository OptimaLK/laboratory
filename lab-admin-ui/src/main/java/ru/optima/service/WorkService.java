package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.Work;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.WorkRepr;
import ru.optima.warning.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public interface WorkService {

    void save(WorkRepr workRepr);

    List<WorkRepr> findAll();

    Optional<WorkRepr> findById(Long id);

    void delete(Long id);

    List<Work> findAllWorksByUserIdWithStatusName(Long userId, String aNew, String on_check);
}
