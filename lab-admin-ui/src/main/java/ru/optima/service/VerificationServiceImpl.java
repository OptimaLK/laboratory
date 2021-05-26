package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.repr.VerificationRepr;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class VerificationServiceImpl implements VerificationService {
    private ArrayList <VerificationRepr> listEquipment;

    @PostConstruct
    public void init() {listEquipment = new ArrayList <>();}

    @Override
    public void addEquipmentToVerification(VerificationRepr verificationRepr) {
        listEquipment.add(verificationRepr);
    }

    @Override
    public ArrayList <VerificationRepr> getEquipmentToVerification() {
        return listEquipment;
    }
}
