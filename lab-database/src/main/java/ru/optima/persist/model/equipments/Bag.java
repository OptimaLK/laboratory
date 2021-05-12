package ru.optima.persist.model.equipments;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
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

    @Column(name = "birth_time")
    private Date birthTime;

    @Column(name = "life_time")
    private Date lifeTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "bags_equipments",
            joinColumns = @JoinColumn(name = "bag_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private List<Equipment> equipments;

    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinTable(name = "bag_user",
            joinColumns = @JoinColumn(name = "bag_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public boolean equipmentsInBag(Equipment equipment){
        for (int i = 0; i < this.getEquipments().size(); i++) {
            if (equipment.equals(this.getEquipments().get(i))) {
                return true;
            }
            else return false;
        }
        return false;
    }

    public Bag(User user) {
        this.name = "Сумка";
        this.birthTime = new Date(System.currentTimeMillis());
        this.lifeTime = new Date(System.currentTimeMillis() + 50000);
        this.equipments = new ArrayList<>();
        this.user = user;
    }
}
