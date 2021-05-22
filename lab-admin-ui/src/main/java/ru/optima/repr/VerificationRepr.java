package ru.optima.repr;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerificationRepr {
    private String customer;
    private String person;
    private String phone;
    private String email;
    private String number;
    private String date;
    private String enterprise;
    private String name_eq;
    private String type_eq;
    private String sum;
    private String number_reg;
    private String factory_num;
    private boolean urgency;
    private boolean protocol;
    private boolean certificate;
    private boolean cal_uncertainty;
    private String note;

    public VerificationRepr(String customer, String person, String phone, String email, String number, String date, String enterprise, String name_eq, String type_eq, String sum, String number_reg, String factory_num, boolean urgency, boolean protocol, boolean certificate, boolean cal_uncertainty, String note) {
        this.customer = customer;
        this.person = person;
        this.phone = phone;
        this.email = email;
        this.number = number;
        this.date = date;
        this.enterprise = enterprise;
        this.name_eq = name_eq;
        this.type_eq = type_eq;
        this.sum = sum;
        this.number_reg = number_reg;
        this.factory_num = factory_num;
        this.urgency = urgency;
        this.protocol = protocol;
        this.certificate = certificate;
        this.cal_uncertainty = cal_uncertainty;
        this.note = note;
    }
}
