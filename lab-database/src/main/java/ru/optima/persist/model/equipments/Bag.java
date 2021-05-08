package ru.optima.persist.model.equipments;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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

//    public Bag() {
//    }
//
//
//    public Bag(Long id, String name, Date birthTime, Date lifeTime, List<Equipment> equipments) {
//        this.id = id;
//        this.name = name;
//        this.birthTime = birthTime;
//        this.lifeTime = lifeTime;
//        this.equipments = equipments;
//    }
//
//    public List<Equipment> getEquipments() {
//        return equipments;
//    }
//
//    public void setEquipments(List<Equipment> equipments) {
//        this.equipments = equipments;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Date getBirthTime() {
//        return birthTime;
//    }
//
//    public void setBirthTime(Date birthTime) {
//        this.birthTime = birthTime;
//    }
//
//    public Date getLifeTime() {
//        return lifeTime;
//    }
//
//    public void setLifeTime(Date lifeTime) {
//        this.lifeTime = lifeTime;
//    }
}
