package ru.optima.repr;

import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.model.User;

import java.sql.Date;
import java.util.List;

public class EquipmentRepr {

    private Long id;
    private String name;
    private String inventoruNumber;
    private String factoryNumber;
    private String verificationNumber;
    private Date verificationDate;
    private Date verificationDateEnd;
    private List<User> users;

    public EquipmentRepr() {
    }

    public EquipmentRepr(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.inventoruNumber = equipment.getInventoruNumber();
        this.factoryNumber = equipment.getFactoryNumber();
        this.verificationNumber = equipment.getVerificationNumber();
        this.verificationDate = equipment.getVerificationDate();
        this.verificationDateEnd = equipment.getVerificationDateEnd();
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

    public String getInventoruNumber() {
        return inventoruNumber;
    }

    public void setInventoruNumber(String inventoruNumber) {
        this.inventoruNumber = inventoruNumber;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(String verificationNumber) {
        this.verificationNumber = verificationNumber;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public Date getVerificationDateEnd() {
        return verificationDateEnd;
    }

    public void setVerificationDateEnd(Date verificationDateEnd) {
        this.verificationDateEnd = verificationDateEnd;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

