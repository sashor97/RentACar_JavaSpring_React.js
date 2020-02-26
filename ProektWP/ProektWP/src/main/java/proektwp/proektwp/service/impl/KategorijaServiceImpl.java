package proektwp.proektwp.service.impl;

import org.springframework.stereotype.Service;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.repository.KategorijaRepository;
import proektwp.proektwp.service.KategorijaService;

import java.util.List;
import java.util.Optional;

@Service
public class KategorijaServiceImpl implements KategorijaService {

    private KategorijaRepository repo;

    public KategorijaServiceImpl( KategorijaRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Kategorija> findAllKategorii() {
        return repo.findAll();
    }

    @Override
    public Kategorija save(Kategorija kategorija) {
        return repo.save(kategorija);
    }

    @Override
    public Optional<Kategorija> findKategorija(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
