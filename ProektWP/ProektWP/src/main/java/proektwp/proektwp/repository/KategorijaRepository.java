package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proektwp.proektwp.models.Kategorija;

import java.util.List;
import java.util.Optional;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

    public List<Kategorija> findAll();

    void deleteById(Long id);

    public Kategorija save(Kategorija kategorija);

    public Optional<Kategorija> findById(Long id);
}
