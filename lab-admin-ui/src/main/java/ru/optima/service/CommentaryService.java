package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.equipments.Category;
import ru.optima.persist.model.equipments.Commentary;

import java.util.List;

@Service
public interface CommentaryService {

    List<Commentary> findAll();

    Commentary findById(Long id);
}
