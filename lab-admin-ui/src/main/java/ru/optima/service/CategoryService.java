package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.persist.model.equipments.Category;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);
}
