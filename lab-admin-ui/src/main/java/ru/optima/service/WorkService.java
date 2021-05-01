package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.repr.WorkRepr;

import java.util.List;
import java.util.Optional;

@Service
public interface WorkService {

    void save(WorkRepr workRepr);

    List<WorkRepr> findAll();

    Optional<WorkRepr> findById(Long id);

    void delete(Long id);
}
