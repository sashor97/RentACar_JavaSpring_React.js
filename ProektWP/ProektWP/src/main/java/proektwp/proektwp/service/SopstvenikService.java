package proektwp.proektwp.service;

import proektwp.proektwp.models.Sopstvenik;

import java.util.List;
import java.util.Optional;

public interface SopstvenikService {
    List<Sopstvenik> findAll();

    Sopstvenik save(Sopstvenik sopstvenik);

    Optional<Sopstvenik> findById(Long id);

    void deleteById(Long id);
}
