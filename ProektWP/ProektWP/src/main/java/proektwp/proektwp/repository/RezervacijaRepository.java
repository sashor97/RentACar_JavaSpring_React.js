package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.models.Rezervacija;

import java.util.List;
import java.util.Optional;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    public List<Rezervacija> findAll();

    public Rezervacija save(Rezervacija rezervacija);

    public Optional<Rezervacija> findById(Long id);

    @Query(value="select r from Rezervacija r where r.vozilo.id =:voziloId")
    public List<Rezervacija> rezervaciiZaVozilo(@Param("voziloId")Long voziloId);

    public void deleteById(Long rezervacijaId);

    @Query(value="select r from Rezervacija r where r.user.id =:userId")
    public List<Rezervacija> rezervaciiZaKorisnik(@Param("userId") Long userId);


}
