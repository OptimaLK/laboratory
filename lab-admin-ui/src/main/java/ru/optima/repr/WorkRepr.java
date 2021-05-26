package ru.optima.repr;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import ru.optima.persist.model.Work;
import ru.optima.persist.model.User;
import ru.optima.persist.model.WorkStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Log4j2
@Data
@RequiredArgsConstructor
@ToString
public class WorkRepr implements Serializable {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date registrationDate;
    private String clientName;
    private String objectName;
    private String numberContract;
    private List<User> users;
    private String customer;
    private WorkStatus workStatus;

    public WorkRepr(Work work) {
        this.id = work.getId();
        this.registrationDate = work.getRegistrationDate();
        this.clientName = work.getClientName();
        this.objectName = work.getObjectName();
        this.numberContract = work.getNumberContract();
        this.users = work.getUsers();
        this.customer = work.getCustomer();
        this.workStatus = work.getWorkStatus();
    }
}

