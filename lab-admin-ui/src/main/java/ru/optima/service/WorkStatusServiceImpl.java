package ru.optima.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.persist.model.WorkStatus;
import ru.optima.persist.repo.WorkStatusRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class WorkStatusServiceImpl implements  WorkStatusService {

    private final WorkStatusRepository workStatusRepository;

    @Override
    public WorkStatus findByName(String name) {
        return workStatusRepository.findByName(name);
    }
}
