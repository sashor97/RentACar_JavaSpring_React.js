package proektwp.proektwp.web.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KategorijaNotFoundException;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.service.KategorijaService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class KategorijaControllerThymeleaf {

    private KategorijaService kategorijaService;

    public KategorijaControllerThymeleaf (KategorijaService kategorijaService){
        this.kategorijaService = kategorijaService;
    }

    @GetMapping("/thymeleaf/kategorija")
    public String Kategorii(Model model) {
        model.addAttribute("kategorijaList", kategorijaService.findAllKategorii());
        model.addAttribute("kategorija", new Kategorija());

        return "kategorii";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/thymeleaf/kategorija/add")
    public String addKategorijaShow(Model model){
        model.addAttribute("kategorijaList", kategorijaService.findAllKategorii());
        model.addAttribute("kategorija", new Kategorija());
        return "kategorija.add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/thymeleaf/kategorija/add/")
    public String addKategorija(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");

        Kategorija newKategorija = new Kategorija();
        newKategorija.setName(name);
        kategorijaService.save(newKategorija);

        model.addAttribute("kategorijaList",kategorijaService.findAllKategorii());
        return "redirect:/thymeleaf/kategorija";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/thymeleaf/delete/kategorija")
    public String deleteKategorija(HttpServletRequest request) {
        Long kategorijaId = Long.parseLong(request.getParameter("kategorijaId"));
        kategorijaService.deleteById(kategorijaId);
        return "redirect:/thymeleaf/kategorija";
    }

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

}
