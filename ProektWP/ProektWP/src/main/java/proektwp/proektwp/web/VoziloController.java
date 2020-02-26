package proektwp.proektwp.web;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.dto.VoziloDto;
import proektwp.proektwp.exceptions.KategorijaNotFoundException;
import proektwp.proektwp.exceptions.ManufacturerNotFoundException;
import proektwp.proektwp.exceptions.SopstvenikNotFoundException;
import proektwp.proektwp.exceptions.VoziloNotFoundException;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.models.Sopstvenik;
import proektwp.proektwp.models.Vozilo;
import proektwp.proektwp.service.KategorijaService;
import proektwp.proektwp.service.ProizvoditelService;
import proektwp.proektwp.service.SopstvenikService;
import proektwp.proektwp.service.VoziloService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class VoziloController {

    private VoziloService voziloService;
    private ProizvoditelService proizvoditelService;
    private KategorijaService kategorijaService;
    private SopstvenikService sopstvenikService;

    public VoziloController(VoziloService voziloService, ProizvoditelService proizvoditelService, KategorijaService kategorijaService, SopstvenikService sopstvenikService){
        this.voziloService = voziloService;
        this.proizvoditelService = proizvoditelService;
        this.sopstvenikService = sopstvenikService;
        this.kategorijaService = kategorijaService;
    }

    @GetMapping("/vozilo")
    public List<Vozilo> getAllVozila (){
        return voziloService.findAll();
    }

    @PostMapping("/vozilo/add")
    public Vozilo addVozilo (HttpServletRequest request, @RequestBody VoziloDto voziloDto) throws ChangeSetPersister.NotFoundException {

        Vozilo vozilo = new Vozilo();

        Proizvoditel proizvoditel1 = proizvoditelService.findById(voziloDto.getProizvoditel()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        vozilo.setProizvoditel(proizvoditel1);
        Kategorija kategorija=kategorijaService.findKategorija(voziloDto.getKategorija()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        vozilo.setKategorija(kategorija);
        Sopstvenik sopstvenik=sopstvenikService.findById(voziloDto.getSopstvenik()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        vozilo.setSopstvenik(sopstvenik);


        vozilo.setCenaPoDen(voziloDto.getCenaPoDen());
        vozilo.setImgUrl(voziloDto.getImgUrl());
        vozilo.setLokacija(voziloDto.getLokacija());
        vozilo.setModel(voziloDto.getModel());

        voziloService.save(vozilo);

//        model.addAttribute("voziloList", voziloService.findAll());
//        return voziloService.save(vozilo);
        return null;
    }

    @ExceptionHandler({VoziloNotFoundException.class})
    @GetMapping("/vozilo/{id}")
    public Optional<Vozilo> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Vozilo v = voziloService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new VoziloNotFoundException());

        return voziloService.findById(ID);

    }

    @GetMapping("vozilo/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Vozilo v = voziloService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new VoziloNotFoundException());

        voziloService.deleteById(ID);

    }

    @GetMapping("vozilo/lokacija/{lokacija}")
    public List<Vozilo> vozilaPoLokacija(@PathVariable("lokacija") String lokacija) {
        return voziloService.findByLokacija(lokacija);

    }

    @ExceptionHandler({ManufacturerNotFoundException.class})
    @GetMapping("vozilo/proizvoditel/{proizvoditelId}")
    public List<Vozilo> vozilaPoProizvoditel(@PathVariable("proizvoditelId") String proizvoditelId) {
        Long ID = Long.parseLong(proizvoditelId);
        Proizvoditel p = proizvoditelService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new ManufacturerNotFoundException());

        return voziloService.vozilaPoProizvoditel(ID);

    }


    @ExceptionHandler({KategorijaNotFoundException.class})
    @GetMapping("vozilo/kategorija/{kategorijaId}")
    public List<Vozilo> vozilaPoKategorija(@PathVariable("kategorijaId") String kategorijaId) {
        Long ID = Long.parseLong(kategorijaId);
        Kategorija k = kategorijaService.findAllKategorii().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new KategorijaNotFoundException());

        return voziloService.vozilaPoKategorija(ID);

    }

    @ExceptionHandler({SopstvenikNotFoundException.class})
    @GetMapping("vozilo/sopstvenik/{sopstvenikId}")
    public List<Vozilo> vozilaPoSopstvenik(@PathVariable("sopstvenikId") String sopstvenikId) {
        Long ID = Long.parseLong(sopstvenikId);
        Sopstvenik s = sopstvenikService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new SopstvenikNotFoundException());

        return voziloService.vozilaPoSopstvenik(ID);

    }




}
