package proektwp.proektwp.service;

import proektwp.proektwp.models.Rezervacija;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface RezervacijaService {
    List<Rezervacija> findAll();

    Rezervacija save(Rezervacija rezervacija) throws ParseException;

    Optional<Rezervacija> findById(Long id);

    List<Rezervacija> rezervaciiZaVozilo(Long voziloId);

    void deleteById(Long rezervacijaId);

    List<Rezervacija> rezervaciiZaKorisnik(Long userId);

    double presmetaj(Rezervacija rezervacija);

    void plati(Rezervacija rezervacija);
}
