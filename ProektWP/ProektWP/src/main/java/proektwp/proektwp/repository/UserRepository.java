package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAll();

    public User save(User user);

    public Optional<User> findById(Long id);

    void deleteById(Long id);
}
