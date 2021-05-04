package ru.optima.persist.model.equipments;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "equipments")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "inventoru_number", nullable = false)
    private String inventoruNumber;

    @Column(name = "factory_number")
    private String factoryNumber;

    @Column(name = "verification_number")
    private String verificationNumber;

    @Column(name = "verification_date")
    private Date verificationDate;

    @Column(name = "verification_date_End")
    private Date verificationDateEnd;

    @Column(name = "status")
    private Status status;

    @Column(name = "free")
    private boolean free;

    @Column(name = "startToUse")
    private Date startToUse;

    @Column(name = "endToUse")
    private Date endToUse;

    public Equipment() {
    }

    public Equipment(Long id, String name, String inventoruNumber, String factoryNumber, String verificationNumber, Date verificationDate, Date verificationDateEnd) {
        this.id = id;
        this.name = name;
        this.inventoruNumber = inventoruNumber;
        this.factoryNumber = factoryNumber;
        this.verificationNumber = verificationNumber;
        this.verificationDate = verificationDate;
        this.verificationDateEnd = verificationDateEnd;
        this.status = Status.WORKING;
        this.free = true;
        this.startToUse = null;
        this.endToUse = null;
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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Date getStartToUse() {
        return startToUse;
    }

    public void setStartToUse(Date startToUse) {
        this.startToUse = startToUse;
    }

    public Date getEndToUse() {
        return endToUse;
    }

    public void setEndToUse(Date endToUse) {
        this.endToUse = endToUse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
