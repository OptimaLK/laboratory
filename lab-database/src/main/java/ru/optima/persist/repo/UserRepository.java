package ru.optima.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.optima.persist.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLastName(String name);

    boolean existsUserByEmail(String email);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);
}
