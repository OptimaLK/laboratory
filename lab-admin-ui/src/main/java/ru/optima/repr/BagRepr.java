package ru.optima.repr;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.optima.persist.model.Protocol;
import ru.optima.persist.model.User;
import ru.optima.persist.model.Work;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class BagRepr {

    private long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date birthTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp lifeTime;
    private int countHourLifeTime;
    private List<Equipment> equipments;
    private User user;
    private Work work;
    private List<Protocol> numberProtocol;
    private int countProtocol;

    public boolean equipmentsInBag(Equipment equipment, Bag bag){
        for (int i = 0; i < bag.getEquipments().size(); i++) {
            if (equipment.equals(bag.getEquipments().get(i))) {
                return true;
            }
        }
        return false;
    }

    public BagRepr(Bag bag) {
        this.id = bag.getId();
        this.name = bag.getName();
        this.birthTime = bag.getBirthTime();
        this.lifeTime = bag.getLifeTime();
        this.countHourLifeTime = 0;
        this.equipments = bag.getEquipments();
        this.user = bag.getUser();
        this.work = bag.getWork();
        this.numberProtocol = bag.getNumberProtocol();

    }
}
