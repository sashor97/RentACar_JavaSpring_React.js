package proektwp.proektwp.service.impl;

import org.springframework.stereotype.Service;
import proektwp.proektwp.models.Rezervacija;
import proektwp.proektwp.models.Vozilo;
import proektwp.proektwp.repository.KategorijaRepository;
import proektwp.proektwp.repository.RezervacijaRepository;
import proektwp.proektwp.repository.VoziloRepository;
import proektwp.proektwp.service.VoziloService;

import java.util.List;
import java.util.Optional;


@Service
public class VoziloServiceImpl implements VoziloService {

    private VoziloRepository repo;


    public VoziloServiceImpl( VoziloRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Vozilo> findAll() {
        return repo.findAll();
    }

    @Override
    public Vozilo save(Vozilo vozilo) {

        return repo.save(vozilo);
    }

    @Override
    public Optional<Vozilo> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long voziloId) {
        repo.deleteById(voziloId);
    }

    @Override
    public List<Vozilo> vozilaPoKategorija(Long kategorijaId) {
        return repo.vozilaPoKategorija(kategorijaId);
    }

    @Override
    public List<Vozilo> vozilaPoProizvoditel(Long proizvoditelId) {
        return repo.vozilaPoProizvoditel(proizvoditelId);
    }

    @Override
    public List<Vozilo> vozilaPoSopstvenik(Long sopstvenikId) {
        return repo.vozilaPoSopstvenik(sopstvenikId);
    }

    @Override
    public List<Vozilo> findByLokacija(String lokacija) {
        return repo.findByLokacija(lokacija);
    }


}
