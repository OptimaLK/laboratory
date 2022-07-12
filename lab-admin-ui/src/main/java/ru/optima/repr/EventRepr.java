package ru.optima.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import ru.optima.persist.model.Event;
import ru.optima.persist.model.User;
import ru.optima.persist.model.Work;
import ru.optima.persist.model.equipments.Equipment;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor
public class EventRepr {

    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp startT;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp endT;
    private List<User> users;
    private List<Work> works;
    private List<Equipment> equipments;

    public EventRepr(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.startT = event.getStartT();
        this.endT = event.getEndT();
        this.users = event.getUsers();
        this.works = event.getWorks();
        this.equipments = event.getEquipments();
    }

}
