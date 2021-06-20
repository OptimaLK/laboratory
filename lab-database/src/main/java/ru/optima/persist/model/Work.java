package ru.optima.persist.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "works")
public class Work implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "number_contract", nullable = false, unique = true)
    private String numberContract;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_works",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @Column(name = "additional_information")
    private String additionalInformation;

    @ManyToOne
    @JoinColumn(name="work_status_id", nullable=false)
    private WorkStatus workStatus;


    @OneToOne(mappedBy = "work")
    private Bag bag;

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @Column(name = "responsible")
    private String responsible;

}
