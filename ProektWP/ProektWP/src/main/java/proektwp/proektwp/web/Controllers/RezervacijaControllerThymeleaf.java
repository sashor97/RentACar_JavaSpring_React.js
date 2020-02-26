package proektwp.proektwp.web.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.UserNotFoundException;
import proektwp.proektwp.exceptions.VoziloNotFoundException;
import proektwp.proektwp.models.Komentar;
import proektwp.proektwp.models.Rezervacija;
import proektwp.proektwp.models.User;
import proektwp.proektwp.models.Vozilo;
import proektwp.proektwp.service.KomentarService;
import proektwp.proektwp.service.RezervacijaService;
import proektwp.proektwp.service.UserService;
import proektwp.proektwp.service.VoziloService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RezervacijaControllerThymeleaf {

    private RezervacijaService rezervacijaService;
    private VoziloService voziloService;
    private UserService userService;

    public RezervacijaControllerThymeleaf (RezervacijaService rezervacijaService, VoziloService voziloService, UserService userService ){
        this.voziloService = voziloService;
        this.rezervacijaService = rezervacijaService;
        this.userService = userService;
    }


    @GetMapping("/thymeleaf/rezervacija/add/")
    public String addRezervacijaShow(HttpServletRequest request,Model model){
        String id = request.getParameter("voziloId");
        model.addAttribute("voziloId", id);
        model.addAttribute("rezervacijaList", rezervacijaService.findAll());
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("rezervacija", new Rezervacija());
        return "rezervacija.vozilo.add";
    }

    @ExceptionHandler({VoziloNotFoundException.class, UserNotFoundException.class})
    @PostMapping("/thymeleaf/rezervacija/add/")
    public String addRezervacija(HttpServletRequest request, Model model) {
        String datumOd = request.getParameter("datumOd");
        int denoviIznajmuvanje = Integer.parseInt(request.getParameter("denoviIznajmuvanje"));

        Long voziloId = Long.parseLong(request.getParameter("vozilo"));
        Optional<Vozilo> vozilo = voziloService.findAll().stream().filter(v -> {
            return voziloId.equals(v.getId());
        }).findAny();

        if (!vozilo.isPresent()) {
            throw new VoziloNotFoundException();
        }

        Long userId = Long.parseLong(request.getParameter("user"));
        Optional<User> user = userService.findAll().stream().filter(v -> {
            return userId.equals(v.getId());
        }).findAny();

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        Rezervacija newRezervacija = new Rezervacija();
        newRezervacija.setDatumOd(datumOd);
        newRezervacija.setDenoviIznajmuvanje(denoviIznajmuvanje);
        newRezervacija.setUser(user.get());
        newRezervacija.setVozilo(vozilo.get());

        double total = rezervacijaService.presmetaj(newRezervacija);


        if (total > 0) {
            model.addAttribute("voziloList", voziloService.findAll());
            return "redirect:/thymeleaf/vozilo";
        }

        else
        {
            return "NevalidnaRezervacija";
        }

    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/thymeleaf/delete/rezervacija")
    public String deleteRezervacija(HttpServletRequest request) {
        Long rezervacijaId = Long.parseLong(request.getParameter("rezervacijaId"));
        rezervacijaService.deleteById(rezervacijaId);
        return "redirect:/thymeleaf/vozilo";
    }

}
