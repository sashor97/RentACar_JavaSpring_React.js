package proektwp.proektwp.web;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.dto.KomentarDto;
import proektwp.proektwp.exceptions.*;
import proektwp.proektwp.models.*;
import proektwp.proektwp.service.KategorijaService;
import proektwp.proektwp.service.KomentarService;
import proektwp.proektwp.service.UserService;
import proektwp.proektwp.service.VoziloService;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class KomentarController {

    private KomentarService komentarService;
    private VoziloService voziloService;
    private UserService userService;

    public KomentarController(KomentarService komentarService, VoziloService voziloService, UserService userService){
        this.komentarService = komentarService;
        this.voziloService = voziloService;
        this.userService = userService;
    }


    @GetMapping("/komentar")
    public List<Komentar> getAllKategorii (){
        return komentarService.findAll();
    }

    @PostMapping("/komentar/add")
    public Komentar addKomentar (@RequestBody KomentarDto komentarDto) throws ChangeSetPersister.NotFoundException {

//        return komentarService.save(komentar);
        Komentar komentar=new Komentar();
        komentar.setOpis(komentarDto.getOpis());
        komentar.setRejting(Double.parseDouble(komentarDto.getRejting()));
        User user=userService.findById(komentarDto.getUser()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        komentar.setUser(user);
        Vozilo vozilo=voziloService.findById(komentarDto.getVozilo()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        komentar.setVozilo(vozilo);
        komentarService.save(komentar);
        return null;
    }

    @ExceptionHandler({KomentarNotFoundException.class})
    @GetMapping("/komentar/{id}")
    public Optional<Komentar> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Komentar kat = komentarService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new KomentarNotFoundException());

        return komentarService.findById(ID);

    }

    @ExceptionHandler({KomentarNotFoundException.class})
    @GetMapping("komentar/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        Komentar k = komentarService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new KomentarNotFoundException());

        komentarService.deleteById(ID);

    }

    @ExceptionHandler({VoziloNotFoundException.class})
    @GetMapping("komentar/vozilo/{voziloId}")
    public List<Komentar> komentarPoVozilo(@PathVariable("voziloId") String voziloId) {
        Long ID = Long.parseLong(voziloId);
        Vozilo p = voziloService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new VoziloNotFoundException());

        return komentarService.komentariZaVozilo(ID);

    }

    @ExceptionHandler({UserNotFoundException.class})
    @GetMapping("komentar/user/{userId}")
    public List<Komentar> komentarPoKorisnik(@PathVariable("userId") String userId) {
        Long ID = Long.parseLong(userId);
        User u = userService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());

        return komentarService.komentariPoKorisnik(ID);

    }

}
