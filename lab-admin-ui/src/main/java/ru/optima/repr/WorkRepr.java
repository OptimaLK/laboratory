package ru.optima.repr;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.optima.persist.model.Work;
import ru.optima.persist.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class WorkRepr implements Serializable {

    private Long id;
    private LocalDate registrationDate;
    private String clientName;
    private String objectName;
    private String numberContract;
    private List<User> users;
    private String customer;

    public WorkRepr(Work work) {
        this.id = work.getId();
        this.registrationDate = work.getRegistrationDate();
        this.clientName = work.getClientName();
        this.objectName = work.getObjectName();
        this.numberContract = work.getNumberContract();
        this.users = work.getUsers();
        this.customer = work.getCustomer();
    }
}

