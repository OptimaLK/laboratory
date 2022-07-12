package ru.optima.persist.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import ru.optima.persist.model.equipments.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "start_t")
    private Timestamp startT;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end_t")
    private Timestamp endT;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "events_works",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "works_id"))
    private List<Work> works;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "events_equipments",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private List<Equipment> equipments;

}
