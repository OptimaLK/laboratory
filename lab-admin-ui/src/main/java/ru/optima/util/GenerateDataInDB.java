package ru.optima.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.optima.persist.model.equipments.Category;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.repo.CategoryRepository;
import ru.optima.persist.repo.EquipmentRepository;

import javax.annotation.PostConstruct;
import java.sql.Date;


// заполнение базы
//
//@Component
public class GenerateDataInDB {
    private CategoryRepository categoryRepository;
    public EquipmentRepository equipmentRepository;

    @Autowired

    public GenerateDataInDB(CategoryRepository categoryRepository, EquipmentRepository equipmentRepository) {
        this.categoryRepository = categoryRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @PostConstruct
    public void generateData () {
        for (int i = 0; i < 5; i++) {
            Category category = new Category("Category " + i);
            categoryRepository.save(category);
            for (int ii = 0; ii < 10; ii++) {
                equipmentRepository.save(
                        new Equipment("Equipment " + i + "|" + ii, "10000" + ii,
                        category, "1" + (ii * i), "1" + (i + ii), Date.valueOf("2012-05-11"),
                        Date.valueOf("2022-05-11")));
            }
        }
    }
}
