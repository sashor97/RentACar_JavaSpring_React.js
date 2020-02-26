package proektwp.proektwp.service.impl;

import org.springframework.stereotype.Service;
import proektwp.proektwp.models.Sopstvenik;
import proektwp.proektwp.repository.KategorijaRepository;
import proektwp.proektwp.repository.SopstvenikRepository;
import proektwp.proektwp.service.SopstvenikService;

import java.util.List;
import java.util.Optional;

@Service
public class SopstvenikServiceImpl implements SopstvenikService {

    private SopstvenikRepository repo;

    public SopstvenikServiceImpl( SopstvenikRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Sopstvenik> findAll() {
        return repo.findAll();
    }

    @Override
    public Sopstvenik save(Sopstvenik sopstvenik) {
        return repo.save(sopstvenik);
    }

    @Override
    public Optional<Sopstvenik> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
