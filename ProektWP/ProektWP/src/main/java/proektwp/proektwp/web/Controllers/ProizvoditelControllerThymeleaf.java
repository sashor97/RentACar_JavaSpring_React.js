package proektwp.proektwp.web.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KategorijaNotFoundException;
import proektwp.proektwp.exceptions.ManufacturerNotFoundException;
import proektwp.proektwp.models.Kategorija;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.service.KategorijaService;
import proektwp.proektwp.service.ProizvoditelService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProizvoditelControllerThymeleaf {
    private ProizvoditelService proizvoditelService;

    public ProizvoditelControllerThymeleaf (ProizvoditelService proizvoditelService){
        this.proizvoditelService = proizvoditelService;
    }

    @GetMapping("/thymeleaf/proizvoditel")
    public String Proizvoditeli(Model model) {
        model.addAttribute("proizvoditelList", proizvoditelService.findAll());
        model.addAttribute("proizvoditel", new Proizvoditel());

        return "proizvoditeli";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/thymeleaf/proizvoditel/add")
    public String addProizvoditelShow(Model model){
        model.addAttribute("proizvoditelList", proizvoditelService.findAll());
        model.addAttribute("proizvoditel", new Proizvoditel());
        return "proizvoditel.add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/thymeleaf/proizvoditel/add/")
    public String addProizvoditel(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");

        Proizvoditel newProizvoditel = new Proizvoditel();
        newProizvoditel.setName(name);
        proizvoditelService.save(newProizvoditel);

        model.addAttribute("proizvoditelList",proizvoditelService.findAll());
        return "redirect:/thymeleaf/proizvoditel";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/thymeleaf/delete/proizvoditel")
    public String deleteProizvoditel(HttpServletRequest request) {
        Long proizvoditelId = Long.parseLong(request.getParameter("proizvoditelId"));
        proizvoditelService.deleteById(proizvoditelId);
        return "redirect:/thymeleaf/proizvoditel";
    }


    @ExceptionHandler({ManufacturerNotFoundException.class})
    @GetMapping("/thymeleaf/proizvoditel/{id}")
    public String Show(@PathVariable("id") String id, Model model){
        Long ID = Long.parseLong(id);

        Proizvoditel proizvoditel = proizvoditelService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new ManufacturerNotFoundException());

        model.addAttribute("proizvoditel",proizvoditel);

        return "proizvoditel.details";
    }

}
