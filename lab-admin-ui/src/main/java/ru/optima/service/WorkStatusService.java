package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.WorkStatus;

@Service
public interface WorkStatusService {

    WorkStatus findByName(String name);

}
