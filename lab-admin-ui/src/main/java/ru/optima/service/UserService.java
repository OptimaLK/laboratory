package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.repr.UserRepr;
import ru.optima.persist.model.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    void save(UserRepr userRepr);

    List<UserRepr> findAll();

    List<User> findAllUsers();

    User findById(Long id);

    void delete(Long id);

    User findByName(String name);

    Optional<User> findByOName(String name);

    void edit(User user);
}
