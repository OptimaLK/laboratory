package ru.optima.persist.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.optima.persist.model.equipments.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "works")
public class Work implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "number_contract", nullable = false, unique = true)
    private String numberContract;

    @ManyToMany(mappedBy = "works")
    private List<User> users;

    @Column(name = "customer")
    private String customer;

}
