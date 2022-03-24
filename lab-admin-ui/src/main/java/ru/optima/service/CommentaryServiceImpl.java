package ru.optima.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.persist.model.equipments.Category;
import ru.optima.persist.model.equipments.Commentary;
import ru.optima.persist.repo.CategoryRepository;
import ru.optima.persist.repo.CommentaryRepository;
import ru.optima.warning.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentaryServiceImpl implements CommentaryService{

    private final CommentaryRepository commentaryRepository;

    @Override
    public List<Commentary> findAll() {
        return commentaryRepository.findAll();
    }

    @Override
    public Commentary findById(Long id) {
        return commentaryRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
