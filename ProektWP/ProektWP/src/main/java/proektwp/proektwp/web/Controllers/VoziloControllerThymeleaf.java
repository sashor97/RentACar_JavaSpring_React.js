package proektwp.proektwp.web.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
import java.util.Optional;

@Controller
public class VoziloControllerThymeleaf {
    private VoziloService voziloService;
    private ProizvoditelService proizvoditelService;
    private KategorijaService kategorijaService;
    private SopstvenikService sopstvenikService;

    public VoziloControllerThymeleaf (VoziloService voziloService, ProizvoditelService proizvoditelService, KategorijaService kategorijaService, SopstvenikService sopstvenikService){
        this.voziloService = voziloService;
        this.proizvoditelService = proizvoditelService;
        this.kategorijaService = kategorijaService;
        this.sopstvenikService = sopstvenikService;
    }

    @GetMapping("/thymeleaf/vozilo")
    public String Vozila(Model model) {
        model.addAttribute("voziloList", voziloService.findAll());
        model.addAttribute("vozilo", new Vozilo());

        return "vozila";
    }


    @ExceptionHandler({VoziloNotFoundException.class})
    @GetMapping("/thymeleaf/vozilo/{id}")
    public String Show(@PathVariable("id") String id, Model model){
        Long ID = Long.parseLong(id);

        Vozilo vozilo = voziloService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new VoziloNotFoundException());

        model.addAttribute("vozilo",vozilo);

        return "vozilo.details";
    }



    @GetMapping("/thymeleaf/vozilo/add")
    public String addVoziloShow(Model model){
        model.addAttribute("voziloList", voziloService.findAll());
        model.addAttribute("proizvoditeli", proizvoditelService.findAll());
        model.addAttribute("kategorii",kategorijaService.findAllKategorii());
        model.addAttribute("sopstvenici",sopstvenikService.findAll());
        model.addAttribute("vozilo", new Vozilo());
        return "vozilo.add";
    }


    @ExceptionHandler({ManufacturerNotFoundException.class, KategorijaNotFoundException.class, SopstvenikNotFoundException.class})
    @PostMapping("/thymeleaf/vozilo/add/")
    public String addVozilo(HttpServletRequest request, Model model) {
        String modell = request.getParameter("model");
        String imgUrl = request.getParameter("imgUrl");
        String lokacija = request.getParameter("lokacija");
        double cenaPoDen = Double.parseDouble(request.getParameter("cenaPoDen"));

        Long manId = Long.parseLong(request.getParameter("proizvoditel"));

        Optional<Proizvoditel> proizvoditel = proizvoditelService.findAll().stream().filter(v->{
            return manId.equals(v.getId());
        }).findAny();

        if (!proizvoditel.isPresent()) {
            throw new ManufacturerNotFoundException();
        }


        Long katId = Long.parseLong(request.getParameter("kategorija"));
        Optional<Kategorija> kategorija = kategorijaService.findAllKategorii().stream().filter(v -> {
            return katId.equals(v.getId());
        }).findAny();

        if (!kategorija.isPresent()) {
            throw new KategorijaNotFoundException();
        }

        Long sopId = Long.parseLong(request.getParameter("sopstvenik"));
        Optional<Sopstvenik> sopstvenik = sopstvenikService.findAll().stream().filter(v -> {
            return sopId.equals(v.getId());
        }).findAny();

        if (!sopstvenik.isPresent()) {
            throw new SopstvenikNotFoundException();
        }

        Vozilo newVozilo = new Vozilo();
        newVozilo.setModel(modell);
        newVozilo.setImgUrl(imgUrl);
        newVozilo.setLokacija(lokacija);
        newVozilo.setCenaPoDen(cenaPoDen);
        newVozilo.setProizvoditel(proizvoditel.get());
        newVozilo.setKategorija(kategorija.get());
        newVozilo.setSopstvenik(sopstvenik.get());

        voziloService.save(newVozilo);

        model.addAttribute("voziloList", voziloService.findAll());
        return "redirect:/thymeleaf/vozilo";

    }



    @DeleteMapping("/thymeleaf/delete/vozilo")
    public String deleteVozilo(HttpServletRequest request) {
        Long voziloId = Long.parseLong(request.getParameter("voziloId"));
        voziloService.deleteById(voziloId);
        return "redirect:/thymeleaf/vozilo";
    }

    /*
    @ExceptionHandler({KategorijaNotFoundException.class})
    @GetMapping("/thymeleaf/kategorija/{id}")
    public String Show(@PathVariable("id") String id, Model model){
        Long ID = Long.parseLong(id);

        Kategorija kategorija = kategorijaService.findAllKategorii().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new KategorijaNotFoundException());

        model.addAttribute("kategorija",kategorija);

        return "kategorija.details";
    }

    */
}
