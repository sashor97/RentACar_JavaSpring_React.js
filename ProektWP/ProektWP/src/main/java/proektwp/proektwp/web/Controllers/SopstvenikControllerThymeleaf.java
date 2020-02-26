package proektwp.proektwp.web.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KategorijaNotFoundException;
import proektwp.proektwp.exceptions.SopstvenikNotFoundException;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Sopstvenik;
import proektwp.proektwp.service.KategorijaService;
import proektwp.proektwp.service.SopstvenikService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SopstvenikControllerThymeleaf {
    private SopstvenikService sopstvenikService;

    public SopstvenikControllerThymeleaf (SopstvenikService sopstvenikService){
        this.sopstvenikService = sopstvenikService;
    }

    @GetMapping("/thymeleaf/sopstvenik")
    public String Sopstvenici(Model model) {
        model.addAttribute("sopstvenikList", sopstvenikService.findAll());
        model.addAttribute("sopstvenik", new Sopstvenik());

        return "sopstvenici";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/thymeleaf/sopstvenik/add")
    public String addSopstvenikShow(Model model){
        model.addAttribute("sopstvenikList", sopstvenikService.findAll());
        model.addAttribute("sopstvenik", new Sopstvenik());
        return "sopstvenik.add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/thymeleaf/sopstvenik/add/")
    public String addSopstvenik(HttpServletRequest request, Model model) {
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String adresa = request.getParameter("adresa");
        int godini  = Integer.parseInt(request.getParameter("godini"));

        Sopstvenik newSopstvenik = new Sopstvenik();
        newSopstvenik.setIme(ime);
        newSopstvenik.setPrezime(prezime);
        newSopstvenik.setAdresa(adresa);
        newSopstvenik.setGodini(godini);
        sopstvenikService.save(newSopstvenik);

        model.addAttribute("sopstvenikList",sopstvenikService.findAll());
        return "redirect:/thymeleaf/sopstvenik";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/thymeleaf/delete/sopstvenik")
    public String deleteSopstvenik(HttpServletRequest request) {
        Long sopstvenikId = Long.parseLong(request.getParameter("sopstvenikId"));
        sopstvenikService.deleteById(sopstvenikId);
        return "redirect:/thymeleaf/sopstvenik";
    }

    @ExceptionHandler({SopstvenikNotFoundException.class})
    @GetMapping("/thymeleaf/sopstvenik/{id}")
    public String Show(@PathVariable("id") String id, Model model){
        Long ID = Long.parseLong(id);

        Sopstvenik sopstvenik = sopstvenikService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new SopstvenikNotFoundException());

        model.addAttribute("sopstvenik",sopstvenik);

        return "sopstvenik.details";
    }

}
