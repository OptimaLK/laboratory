package ru.optima.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.optima.persist.model.equipments.Category;
import ru.optima.persist.model.equipments.Commentary;
import ru.optima.persist.model.equipments.Equipment;
import ru.optima.persist.model.User;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EquipmentRepr {

    private Long id;
    private String name;
    private String inventoryNumber;
    private String factoryNumber;
    private String verificationNumber;
    private Date verificationDate;
    private Date verificationDateEnd;
    private List<User> users;
    private boolean activ;
    private Boolean taken;
    private String nameUserWhoTakenEquipment;
    private Category category;
    private Commentary commentary;
    private String mistake;
    private String span;

    public EquipmentRepr(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.inventoryNumber = equipment.getInventoryNumber();
        this.factoryNumber = equipment.getFactoryNumber();
        this.verificationNumber = equipment.getVerificationNumber();
        this.verificationDate = equipment.getVerificationDate();
        this.verificationDateEnd = equipment.getVerificationDateEnd();
        this.activ = equipment.isActiv();
        this.taken = equipment.getTaken();
        this.nameUserWhoTakenEquipment = equipment.getNameUserWhoTakenEquipment();
        this.category = equipment.getCategory();
        this.commentary = equipment.getCommentary();
        this.mistake = equipment.getMistake();
        this.span = equipment.getSpan();
    }
}

