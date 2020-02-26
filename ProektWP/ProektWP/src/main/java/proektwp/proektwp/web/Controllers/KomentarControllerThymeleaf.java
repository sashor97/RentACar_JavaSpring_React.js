package proektwp.proektwp.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.*;
import proektwp.proektwp.models.*;
import proektwp.proektwp.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class KomentarControllerThymeleaf {

    private KomentarService komentarService;
    private VoziloService voziloService;
    private UserService userService;

    public KomentarControllerThymeleaf (KomentarService komentarService, VoziloService voziloService, UserService userService ){
        this.voziloService = voziloService;
        this.komentarService = komentarService;
        this.userService = userService;
    }


    @GetMapping("/thymeleaf/komentar/add/vozilo/{id}")
    public String addKomentarShow(@PathVariable("id") String id, Model model){
        model.addAttribute("voziloId", id);
        model.addAttribute("komentarList", komentarService.findAll());
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("komentar", new Komentar());
        return "komentar.vozilo.add";
    }


    @GetMapping("/thymeleaf/komentar/add")
    public String addKomentarShow(Model model){
        model.addAttribute("voziloList", voziloService.findAll());
        model.addAttribute("komentarList", komentarService.findAll());
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("komentar", new Komentar());
        return "komentar.add";
    }

    @ExceptionHandler({VoziloNotFoundException.class, UserNotFoundException.class})
    @PostMapping("/thymeleaf/komentar/add/")
    public String addKomentar(HttpServletRequest request, Model model) {
        String opis = request.getParameter("opis");
        double rejting = Double.parseDouble(request.getParameter("rejting"));

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

        Komentar newKomentar = new Komentar();
        newKomentar.setOpis(opis);
        newKomentar.setRejting(rejting);
        newKomentar.setUser(user.get());
        newKomentar.setVozilo(vozilo.get());

        komentarService.save(newKomentar);

        model.addAttribute("voziloList", voziloService.findAll());
        return "redirect:/thymeleaf/vozilo";

    }

    @DeleteMapping("/thymeleaf/delete/komentar")
    public String deleteVozilo(HttpServletRequest request) {
        Long komentarId = Long.parseLong(request.getParameter("komentarId"));
        komentarService.deleteById(komentarId);
        return "redirect:/thymeleaf/vozilo";
    }

}
