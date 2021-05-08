package ru.optima.persist.model.equipments;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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

//    public Equipment() {
//    }
//
//    public Equipment(Long id, String name, String inventoruNumber, String factoryNumber, String verificationNumber, Date verificationDate, Date verificationDateEnd) {
//        this.id = id;
//        this.name = name;
//        this.inventoruNumber = inventoruNumber;
//        this.factoryNumber = factoryNumber;
//        this.verificationNumber = verificationNumber;
//        this.verificationDate = verificationDate;
//        this.verificationDateEnd = verificationDateEnd;
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
//    public String getInventoruNumber() {
//        return inventoruNumber;
//    }
//
//    public void setInventoruNumber(String inventoruNumber) {
//        this.inventoruNumber = inventoruNumber;
//    }
//
//    public String getFactoryNumber() {
//        return factoryNumber;
//    }
//
//    public void setFactoryNumber(String factoryNumber) {
//        this.factoryNumber = factoryNumber;
//    }
//
//    public String getVerificationNumber() {
//        return verificationNumber;
//    }
//
//    public void setVerificationNumber(String verificationNumber) {
//        this.verificationNumber = verificationNumber;
//    }
//
//    public Date getVerificationDate() {
//        return verificationDate;
//    }
//
//    public void setVerificationDate(Date verificationDate) {
//        this.verificationDate = verificationDate;
//    }
//
//    public Date getVerificationDateEnd() {
//        return verificationDateEnd;
//    }
//
//    public void setVerificationDateEnd(Date verificationDateEnd) {
//        this.verificationDateEnd = verificationDateEnd;
//    }

}
