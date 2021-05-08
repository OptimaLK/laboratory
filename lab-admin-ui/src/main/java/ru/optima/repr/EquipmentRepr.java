package ru.optima.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.model.User;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EquipmentRepr {

    private Long id;
    private String name;
    private String inventoruNumber;
    private String factoryNumber;
    private String verificationNumber;
    private Date verificationDate;
    private Date verificationDateEnd;
    private List<User> users;

    public EquipmentRepr(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.inventoruNumber = equipment.getInventoruNumber();
        this.factoryNumber = equipment.getFactoryNumber();
        this.verificationNumber = equipment.getVerificationNumber();
        this.verificationDate = equipment.getVerificationDate();
        this.verificationDateEnd = equipment.getVerificationDateEnd();
    }
}

