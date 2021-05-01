package ru.optima.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.repr.EquipmentRepr;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PackageEquipments {
    private List<Equipment> equipments;
    private int count = 1;

    @PostConstruct
    public void init() {equipments = new ArrayList<>();}

    public void clear(){
        equipments.clear();
    }

    public void add(Equipment equipment){
        for (Equipment e : equipments) {
            if (e.getId().equals(equipment.getId())){
                return;
            }
        }
        equipments.add(equipment);
    }

    public PackageEquipments() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void recolculateCount() {
        count++;
    }
}
