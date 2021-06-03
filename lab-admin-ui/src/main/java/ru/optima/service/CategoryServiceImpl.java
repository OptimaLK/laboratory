package ru.optima.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.persist.model.equipments.Category;
import ru.optima.persist.repo.CategoryRepository;
import ru.optima.warning.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
