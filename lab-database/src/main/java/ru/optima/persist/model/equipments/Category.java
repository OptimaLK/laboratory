package ru.optima.persist.model.equipments;

import javax.persistence.*;
import java.util.List;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @Column(name = "equipments")
    private List<Equipment> equipmens;

    public Category(Long id, String name, List<Equipment> equipmens) {
        this.id = id;
        this.name = name;
        this.equipmens = equipmens;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {

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

    public List<Equipment> getEquipmens() {
        return equipmens;
    }

    public void setEquipmens(List<Equipment> equipmens) {
        this.equipmens = equipmens;
    }
}
