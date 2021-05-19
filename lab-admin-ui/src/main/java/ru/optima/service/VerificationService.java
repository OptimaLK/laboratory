package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.repr.VerificationRepr;

import java.util.ArrayList;

@Service
public interface VerificationService {
    void addEquipmentToVerification(VerificationRepr verificationRepr);
    ArrayList<VerificationRepr> getEquipmentToVerification();
}
