package ru.optima.persist.model.equipments;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.optima.persist.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bag")
public class Bag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "birth_time")
    private Date birthTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "life_time")
    private Date lifeTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "bags_equipments",
            joinColumns = @JoinColumn(name = "bag_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private List<Equipment> equipments;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    private User user;

    public Bag(User user) {
        this.name = "Сумка";
        this.birthTime = new Date(System.currentTimeMillis());
        this.lifeTime = new Date(System.currentTimeMillis() + 50000);
        this.equipments = new ArrayList<>();
        this.user = user;
    }
}
