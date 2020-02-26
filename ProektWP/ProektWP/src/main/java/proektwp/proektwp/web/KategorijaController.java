package proektwp.proektwp.web;

import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KategorijaNotFoundException;
import proektwp.proektwp.exceptions.VoziloNotFoundException;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Vozilo;
import proektwp.proektwp.service.KategorijaService;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class KategorijaController {

    private KategorijaService kategorijaService;

    public KategorijaController(KategorijaService kategorijaService){
        this.kategorijaService = kategorijaService;
    }


    @GetMapping("/kategorija")
    public List<Kategorija> getAllKategorii (){
        return kategorijaService.findAllKategorii();
    }

    @PostMapping("/kategorija/add")
    public Kategorija addKategorija (@RequestBody Kategorija kategorija){
        return kategorijaService.save(kategorija);
    }

    @ExceptionHandler({KategorijaNotFoundException.class})
    @GetMapping("/kategorija/{id}")
    public Optional<Kategorija> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Kategorija kat = kategorijaService.findAllKategorii().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new KategorijaNotFoundException());

        return kategorijaService.findKategorija(ID);

    }
    @GetMapping("kategorija/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Kategorija k= kategorijaService.findAllKategorii().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new KategorijaNotFoundException());

       kategorijaService.deleteById(ID);

    }




}
