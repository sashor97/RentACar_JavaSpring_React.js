package proektwp.proektwp.service;

import proektwp.proektwp.models.Kategorija;

import java.util.List;
import java.util.Optional;

public interface KategorijaService {
    List<Kategorija> findAllKategorii();

    Kategorija save(Kategorija kategorija);

    Optional<Kategorija> findKategorija(Long id);

    void deleteById(Long id);
}
