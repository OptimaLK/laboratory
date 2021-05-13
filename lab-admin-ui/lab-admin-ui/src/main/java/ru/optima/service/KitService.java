package ru.optima.service;

import ru.optima.persist.model.equipments.Kit;
import ru.optima.repr.KitRepr;

import java.util.List;

public interface KitService {
    void save(KitRepr kitRepr);

    List<KitRepr> findAll();

    Kit findById(Long id);

    Kit findByName(String name);
}
