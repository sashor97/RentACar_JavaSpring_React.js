package proektwp.proektwp.service;

import proektwp.proektwp.models.Proizvoditel;

import java.util.List;
import java.util.Optional;

public interface ProizvoditelService {
    List<Proizvoditel> findAll();

    Proizvoditel save(Proizvoditel proizvoditel);

    Optional<Proizvoditel> findById(Long id);

    void deleteById(Long id);
}
