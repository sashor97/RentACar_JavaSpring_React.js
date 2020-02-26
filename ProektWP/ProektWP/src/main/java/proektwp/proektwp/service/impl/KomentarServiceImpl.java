package proektwp.proektwp.service.impl;

import org.springframework.stereotype.Service;
import proektwp.proektwp.models.Komentar;
import proektwp.proektwp.models.Vozilo;
import proektwp.proektwp.repository.KomentarRepository;
import proektwp.proektwp.service.KomentarService;

import java.util.List;
import java.util.Optional;

@Service
public class KomentarServiceImpl implements KomentarService {

    private KomentarRepository repo;

    public KomentarServiceImpl( KomentarRepository repo){
        this.repo = repo;
    }


    @Override
    public List<Komentar> findAll() {
        return repo.findAll();
    }

    @Override
    public Komentar save(Komentar komentar) {
        return repo.save(komentar);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Komentar> komentariZaVozilo(Long voziloId) {
        return repo.komentariZaVozilo(voziloId);
    }

    @Override
    public List<Komentar> komentariPoKorisnik(Long userId) {
        return repo.komentariPoKorisnik(userId);
    }


    @Override
    public Optional<Komentar> findById(Long id) {
        return repo.findById(id);
    }
}
