package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.models.Vozilo;

import java.util.List;
import java.util.Optional;

public interface VoziloRepository extends JpaRepository<Vozilo, Long> {
    public List<Vozilo> findAll();

    public Vozilo save(Vozilo vozilo);

    public Optional<Vozilo> findById(Long id);

    public void deleteById(Long voziloId);

    @Query(value="select v from Vozilo v where v.kategorija.id =:kategorijaId")
    public List<Vozilo> vozilaPoKategorija(@Param("kategorijaId")Long kategorijaId);

    @Query(value="select v from Vozilo v where v.proizvoditel.id =:proizvoditelId")
    public List<Vozilo> vozilaPoProizvoditel(@Param("proizvoditelId") Long proizvoditelId);

    @Query(value="select v from Vozilo v where v.sopstvenik.id =:sopstvenikId")
    public List<Vozilo> vozilaPoSopstvenik(@Param("sopstvenikId") Long sopstvenikId);


    List<Vozilo> findByLokacija(String lokacija);
}
