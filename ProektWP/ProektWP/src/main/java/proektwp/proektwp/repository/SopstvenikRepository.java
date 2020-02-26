package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Sopstvenik;

import java.util.List;
import java.util.Optional;

public interface SopstvenikRepository extends JpaRepository<Sopstvenik, Long> {
    public List<Sopstvenik> findAll();

    public Sopstvenik save(Sopstvenik sopstvenik);

    public Optional<Sopstvenik> findById(Long id);

    void deleteById(Long id);
}
