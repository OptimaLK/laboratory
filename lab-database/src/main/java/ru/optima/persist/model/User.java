package ru.optima.persist.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.Equipment;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(length = 128, name = "email")
    private String email;

    @Column(length = 32, name = "first_name")
    private String firstName;

    @Column(length = 32, name = "last_name")
    private String lastName;

    @Column(length = 32, name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_works",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id"))
    private List<Work> works;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "users_bags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bag_id"))
    private List<Bag> bags;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
