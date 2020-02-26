package proektwp.proektwp.web;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.dto.RezervacijaDto;
import proektwp.proektwp.dto.VoziloDto;
import proektwp.proektwp.exceptions.KomentarNotFoundException;
import proektwp.proektwp.exceptions.RezervacijaNotFoundException;
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
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class RezervacijaController {

    private RezervacijaService rezervacijaService;
    private VoziloService voziloService;
    private UserService userService;

    public RezervacijaController(RezervacijaService rezervacijaService, VoziloService voziloService, UserService userService){
        this.rezervacijaService = rezervacijaService;
        this.voziloService = voziloService;
        this.userService = userService;
    }


    @GetMapping("/rezervacija")
    public List<Rezervacija> getAllRezervacii (){
        return rezervacijaService.findAll();
    }

    @PostMapping("/rezervacija/add")
    public Rezervacija addRezervacija (HttpServletRequest request, @RequestBody RezervacijaDto rezervacijaDto) throws ParseException, ChangeSetPersister.NotFoundException {
//        return rezervacijaService.save(rezervacija);
        Rezervacija rezervacija=new Rezervacija();
        rezervacija.setDatumOd(rezervacijaDto.getDatumOd());
        rezervacija.setDenoviIznajmuvanje(rezervacijaDto.getDenoviIznajmuvanje());
        User user=userService.findById(rezervacijaDto.getUser()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        rezervacija.setUser(user);
        Vozilo vozilo=voziloService.findById(rezervacijaDto.getVozilo()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        rezervacija.setVozilo(vozilo);
        rezervacija.setDatumDo(rezervacijaDto.getDatumDo());
        rezervacijaService.presmetaj(rezervacija);
        return null;
    }

    @ExceptionHandler({RezervacijaNotFoundException.class})
    @GetMapping("/rezervacija/{id}")
    public Optional<Rezervacija> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Rezervacija rez = rezervacijaService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new RezervacijaNotFoundException());

        return rezervacijaService.findById(ID);

    }

    @ExceptionHandler({RezervacijaNotFoundException.class})
    @GetMapping("rezervacija/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Rezervacija r = rezervacijaService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new RezervacijaNotFoundException());

        rezervacijaService.deleteById(ID);

    }

    @ExceptionHandler({VoziloNotFoundException.class})
    @GetMapping("rezervacija/vozilo/{voziloId}")
    public List<Rezervacija> rezervacijaPoVozilo(@PathVariable("voziloId") String voziloId) {
        Long ID = Long.parseLong(voziloId);
        Vozilo p = voziloService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new VoziloNotFoundException());

        return rezervacijaService.rezervaciiZaVozilo(ID);

    }

    @ExceptionHandler({UserNotFoundException.class})
    @GetMapping("rezervacija/user/{userId}")
    public List<Rezervacija> rezervacijaPoKorisnik(@PathVariable("userId") String userId) {
        Long ID = Long.parseLong(userId);
        User u = userService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());

        return rezervacijaService.rezervaciiZaKorisnik(ID);

    }

    @ExceptionHandler({RezervacijaNotFoundException.class})
    @GetMapping("/rezervacija/total/{id}")
    public double suma(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Rezervacija rez = rezervacijaService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new RezervacijaNotFoundException());

        return rezervacijaService.presmetaj(rez);

    }




}
