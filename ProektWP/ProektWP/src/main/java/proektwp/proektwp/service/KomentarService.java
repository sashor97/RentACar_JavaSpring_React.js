package proektwp.proektwp.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proektwp.proektwp.models.Komentar;
import proektwp.proektwp.models.Vozilo;

import java.util.List;
import java.util.Optional;

public interface KomentarService {
    List<Komentar> findAll();

    Komentar save(Komentar komentar);

    void deleteById(Long id);

    List<Komentar> komentariZaVozilo(Long voziloId);

    List<Komentar> komentariPoKorisnik(Long userId);

    Optional<Komentar> findById(Long id);
}
