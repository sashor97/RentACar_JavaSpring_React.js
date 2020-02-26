package proektwp.proektwp.web;

import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KomentarNotFoundException;
import proektwp.proektwp.exceptions.ManufacturerNotFoundException;
import proektwp.proektwp.exceptions.SopstvenikNotFoundException;
import proektwp.proektwp.models.Komentar;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.models.Sopstvenik;
import proektwp.proektwp.service.ProizvoditelService;
import proektwp.proektwp.service.SopstvenikService;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class SopstvenikController {

    private SopstvenikService sopstvenikService;

    public SopstvenikController(SopstvenikService sopstvenikService){
        this.sopstvenikService = sopstvenikService;
    }

    @GetMapping("/sopstvenik")
    public List<Sopstvenik> getAllSopstvenici (){
        return sopstvenikService.findAll();
    }

    @PostMapping("/sopstvenik/add")
    public Sopstvenik addSopstvenik (@RequestBody Sopstvenik sopstvenik){
        return sopstvenikService.save(sopstvenik);
    }

    @ExceptionHandler({SopstvenikNotFoundException.class})
    @GetMapping("/sopstvenik/{id}")
    public Optional<Sopstvenik> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Sopstvenik sop = sopstvenikService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new SopstvenikNotFoundException());

        return sopstvenikService.findById(ID);

    }
    @GetMapping("sopstvenik/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Sopstvenik s = sopstvenikService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new SopstvenikNotFoundException());

        sopstvenikService.deleteById(ID);

    }
}
