package ru.optima.repr;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Equipment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class BagRepr {

    private long id;
    private String name;
    private Date birthTime;
    private Date lifeTime;
    private List<Equipment> equipments;
    private User user;

    public BagRepr(User user, Equipment equipment) {
        this.id = id;
        this.name = "Сумка #" + id;
        this.birthTime = new Date(System.currentTimeMillis());
        this.lifeTime = new Date(System.currentTimeMillis() + 50000);
        this.equipments = new ArrayList<>();
        this.user = user;
    }



}
