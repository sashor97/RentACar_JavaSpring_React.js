package proektwp.proektwp.service;

import proektwp.proektwp.models.Vozilo;

import java.util.List;
import java.util.Optional;

public interface VoziloService {
    List<Vozilo> findAll();

    Vozilo save(Vozilo vozilo);

    Optional<Vozilo> findById(Long id);

    void deleteById(Long voziloId);


    List<Vozilo> vozilaPoKategorija(Long kategorijaId);


    List<Vozilo> vozilaPoProizvoditel(Long proizvoditelId);


    List<Vozilo> vozilaPoSopstvenik(Long sopstvenikId);

    List<Vozilo> findByLokacija(String lokacija);
}
