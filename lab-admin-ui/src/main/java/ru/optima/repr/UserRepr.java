package ru.optima.repr;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.optima.persist.model.Work;
import ru.optima.persist.model.Role;
import ru.optima.persist.model.User;
import ru.optima.persist.model.equipments.Bag;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
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

    private List<Bag> bag;

    public UserRepr(User user) {
        this.id = user.getId();
        this.bag = user.getBags();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
        this.works = user.getWorks();
    }
}
