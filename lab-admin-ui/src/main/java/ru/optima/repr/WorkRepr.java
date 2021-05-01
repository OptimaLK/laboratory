package ru.optima.repr;

import ru.optima.persist.model.Work;
import ru.optima.persist.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class WorkRepr implements Serializable {

    private Long id;
    private LocalDate registrationDate;
    private String clientName;
    private String objectName;
    private String numberContract;
    private List<User> users;
    private String customer;

    public WorkRepr() {
    }

    public WorkRepr(Work work) {
        this.id = work.getId();
        this.registrationDate = work.getRegistrationDate();
        this.clientName = work.getClientName();
        this.objectName = work.getObjectName();
        this.numberContract = work.getNumberContract();
        this.users = work.getUsers();
        this.customer = work.getCustomer();
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

