package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proektwp.proektwp.models.Komentar;
import proektwp.proektwp.models.Vozilo;

import java.util.List;
import java.util.Optional;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    List<Komentar> findAll();

    Komentar save(Komentar komentar);

    void deleteById(Long id);

    @Query(value="select k from Komentar k where k.vozilo.id =:voziloId")
    List<Komentar> komentariZaVozilo(@Param("voziloId") Long voziloId);

    @Query(value="select k from Komentar k where k.user.id =:userId")
    List<Komentar> komentariPoKorisnik(@Param("userId") Long userId);

    Optional<Komentar> findById(Long id);
}
