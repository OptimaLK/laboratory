package ru.optima.persist.model;

import ru.optima.persist.model.equipments.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


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

    public Work() {
    }

    public Work(Long id, LocalDate registrationDate, String clientName, String objectName, String numberContract, List<User> users, List<Equipment> equipment, String customer) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.clientName = clientName;
        this.objectName = objectName;
        this.numberContract = numberContract;
        this.users = users;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getNumberContract() {
        return numberContract;
    }

    public void setNumberContract(String numberContract) {
        this.numberContract = numberContract;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
