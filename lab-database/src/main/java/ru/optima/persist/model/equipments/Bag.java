package ru.optima.persist.model.equipments;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "bag")
public class Bag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_Time")
    private Date birthTime;

    @Column(name = "life_Time")
    private Date lifeTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "bags_kits",
            joinColumns = @JoinColumn(name = "bag_id"),
            inverseJoinColumns = @JoinColumn(name = "kit_id"))
    private List<Kit> kits;

    public Bag() {
    }

    public Bag(Long id, String name, Date birthTime, Date lifeTime, List<Kit> kits) {
        this.id = id;
        this.name = name;
        this.birthTime = birthTime;
        this.lifeTime = lifeTime;
        this.kits = kits;
    }

    public List<Kit> getKits() {
        return kits;
    }

    public void setKits(List<Kit> kits) {
        this.kits = kits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Date birthTime) {
        this.birthTime = birthTime;
    }

    public Date getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Date lifeTime) {
        this.lifeTime = lifeTime;
    }
}
