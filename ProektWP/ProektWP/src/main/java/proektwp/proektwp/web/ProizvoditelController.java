package proektwp.proektwp.web;

import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KategorijaNotFoundException;
import proektwp.proektwp.exceptions.ManufacturerNotFoundException;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.service.KategorijaService;
import proektwp.proektwp.service.ProizvoditelService;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class ProizvoditelController {

    private ProizvoditelService proizvoditelService;

    public ProizvoditelController(ProizvoditelService proizvoditelService){
        this.proizvoditelService = proizvoditelService;
    }

    @GetMapping("/proizvoditel")
    public List<Proizvoditel> getAllProizvoditeli (){
        return proizvoditelService.findAll();
    }

    @PostMapping("/proizvoditel/add")
    public Proizvoditel addProizvoditel (@RequestBody Proizvoditel proizvoditel){
        return proizvoditelService.save(proizvoditel);
    }

    @ExceptionHandler({ManufacturerNotFoundException.class})
    @GetMapping("/proizvoditel/{id}")
    public Optional<Proizvoditel> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Proizvoditel pr = proizvoditelService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new ManufacturerNotFoundException());

        return proizvoditelService.findById(ID);

    }
    @GetMapping("proizvoditel/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Proizvoditel p= proizvoditelService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new ManufacturerNotFoundException());

        proizvoditelService.deleteById(ID);

    }
}
