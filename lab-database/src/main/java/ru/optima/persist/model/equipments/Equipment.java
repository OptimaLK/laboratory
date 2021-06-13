package ru.optima.persist.model.equipments;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "inventory_number", nullable = false)
    private String inventoryNumber;

    @Column(name = "factory_number")
    private String factoryNumber;

    @Column(name = "verification_number")
    private String verificationNumber;

    @Column(name = "verification_date")
    private Date verificationDate;

    @Column(name = "verification_date_end")
    private Date verificationDateEnd;

    @Transient
    private boolean activ = false;

    @Column(name = "taken")
    private Boolean taken;

    @Column(name = "name_user_who_taken_equipment")
    private String nameUserWhoTakenEquipment;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "bags_equipments",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "bag_id"))
    private List<Bag> bags;
}
