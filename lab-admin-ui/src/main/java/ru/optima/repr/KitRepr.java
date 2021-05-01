package ru.optima.repr;

import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.model.equipments.Kit;

import java.util.*;

public class KitRepr {

    private Long id;
    private List<Equipment> equipments;
    private String name;
    private User user;

    public KitRepr() {
    }

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

    public User getUser() {
        return user;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
