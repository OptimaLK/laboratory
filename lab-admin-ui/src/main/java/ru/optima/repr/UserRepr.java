package ru.optima.repr;

import ru.optima.persist.model.Work;
import ru.optima.persist.model.Role;
import ru.optima.persist.model.User;
import ru.optima.utils.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserRepr {

    private Long id;

    @NotEmpty
    private String password;

    @Size(min = 3, message = "Имя слишком короткое")
    private String firstName;

    @NotEmpty
    @Size(min = 3, message = "Фамилия слишком короткое")
    private String lastName;

    @Size(min = 10, max = 12, message = "Неверный формат телефона")
    private String phone;

    @Size(min = 5, message = "Email слишком короткий")
    @Email
    private String email;

    private Collection<Role> roles;

    private List<Work> works;

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
        this.works = user.getWorks();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    @Override
    public String toString() {
        return "UserRepr{" +
                "id=" + id +
                ", username='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
