package ru.optima.persist.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.persist.model.equipments.DisableBags;

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

    @OneToOne(mappedBy = "user", optional = false)
    private Bag bag;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<DisableBags> disableBags;

//    public User(Long id, String password, String email, String firstName, String lastName, String phone, Collection<Role> roles, List<Work> works) {
//        this.id = id;
//        this.password = password;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.roles = roles;
//        this.works = works;
//    }
//
//    public User() {
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
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//    public Collection<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Collection<Role> roles) {
//        this.roles = roles;
//    }
//
//    public List<Work> getWorks() {
//        return works;
//    }
//
//    public void setWorks(List<Work> works) {
//        this.works = works;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
}
