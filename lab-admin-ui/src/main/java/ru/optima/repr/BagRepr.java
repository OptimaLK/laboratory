package ru.optima.repr;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.optima.beans.PackageEquipments;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;
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

    public boolean equipmentsInBag(Equipment equipment, Bag bag){
        for (int i = 0; i < bag.getEquipments().size(); i++) {
            if (equipment.equals(bag.getEquipments().get(i))) {
                return true;
            }
            else return false;
        }
        return false;
    }

    public BagRepr(User user, Equipment equipment) {
        this.id = id;
        this.name = "Сумка #" + id;
        this.birthTime = new Date(System.currentTimeMillis());
        this.lifeTime = new Date(System.currentTimeMillis() + 50000);
        this.equipments = new ArrayList<>();
        this.user = user;
    }


    public BagRepr(Bag bag) {
        this.id = bag.getId();
        this.name = bag.getName();
        this.birthTime = bag.getBirthTime();
        this.lifeTime = bag.getLifeTime();
        this.equipments = bag.getEquipments();
        this.user = bag.getUser();
    }
}
