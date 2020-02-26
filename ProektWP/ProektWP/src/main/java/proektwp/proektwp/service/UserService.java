package proektwp.proektwp.service;

import proektwp.proektwp.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
