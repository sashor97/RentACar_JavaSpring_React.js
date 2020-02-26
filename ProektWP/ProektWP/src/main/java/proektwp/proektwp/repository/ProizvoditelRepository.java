package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Proizvoditel;

import java.util.List;
import java.util.Optional;

public interface ProizvoditelRepository extends JpaRepository<Proizvoditel, Long> {
    public List<Proizvoditel> findAll();

    public Proizvoditel save(Proizvoditel proizvoditel);

    void deleteById(Long id);

    public Optional<Proizvoditel> findById(Long id);
}
