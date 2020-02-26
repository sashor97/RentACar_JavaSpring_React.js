package proektwp.proektwp.service.impl;

import org.springframework.stereotype.Service;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.repository.KategorijaRepository;
import proektwp.proektwp.repository.ProizvoditelRepository;
import proektwp.proektwp.service.ProizvoditelService;

import java.util.List;
import java.util.Optional;

@Service
public class ProizvoditelServiceImpl implements ProizvoditelService {

    private ProizvoditelRepository repo;

    public ProizvoditelServiceImpl( ProizvoditelRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Proizvoditel> findAll() {
        return repo.findAll();
    }

    @Override
    public Proizvoditel save(Proizvoditel proizvoditel) {
        return repo.save(proizvoditel);
    }

    @Override
    public Optional<Proizvoditel> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
