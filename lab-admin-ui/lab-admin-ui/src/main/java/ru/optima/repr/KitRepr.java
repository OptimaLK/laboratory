package ru.optima.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.model.equipments.Kit;

import java.util.*;

@Data
@NoArgsConstructor
public class KitRepr {

    private Long id;
    private List<Equipment> equipments;
    private String name;
    private User user;

    public KitRepr(User user, PackageEquipments pEquipments) {
        this.user = user;
        this.name = "Комплет №" + pEquipments.getCount();
        this.equipments = new ArrayList<>();
        this.equipments.addAll(pEquipments.getEquipments());
        pEquipments.recolculateCount();
        pEquipments.clear();

    }

    public KitRepr(Kit kit) {
        this.id = kit.getId();
        this.equipments = kit.getEquipments();
        this.name = kit.getName();
        this.user = kit.getUser();
    }

}
