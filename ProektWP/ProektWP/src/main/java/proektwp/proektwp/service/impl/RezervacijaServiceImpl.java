package proektwp.proektwp.service.impl;

import org.springframework.stereotype.Service;
import proektwp.proektwp.exceptions.ReservationNotValidException;
import proektwp.proektwp.models.Rezervacija;

import proektwp.proektwp.repository.RezervacijaRepository;
import proektwp.proektwp.repository.VoziloRepository;
import proektwp.proektwp.service.RezervacijaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;



@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository repo;
    private VoziloRepository voziloRepo;

    public RezervacijaServiceImpl( RezervacijaRepository repo, VoziloRepository voziloRepo ){
        this.repo = repo;
        this.voziloRepo = voziloRepo;
    }

    @Override
    public List<Rezervacija> findAll() {
        return repo.findAll();
    }

    @Override
    public Rezervacija save(Rezervacija rezervacija)  {
//        List<Rezervacija> rezervacii = rezervacija.getVozilo().getRezervacii();
//
//        if (rezervacii != null) {
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//
//            for (Rezervacija r : rezervacii) {
//                LocalDate datumOd = LocalDate.parse(r.getDatumOd(),formatter);
//                LocalDate datumDo = datumOd.plusDays(r.getDenoviIznajmuvanje());
//
//                LocalDate momentalnaRezDatumOd = LocalDate.parse(rezervacija.getDatumOd(),formatter);
//                LocalDate momentalnaRezDatumDo = momentalnaRezDatumOd.plusDays(rezervacija.getDenoviIznajmuvanje());
//
//
//                if (datumOd.isBefore(momentalnaRezDatumOd) && datumDo.isAfter(momentalnaRezDatumOd)) {
//                    throw new ReservationNotValidException();
//                }
//
//                if (datumOd.isBefore(momentalnaRezDatumDo) && datumDo.isAfter(momentalnaRezDatumDo)) {
//                    throw new ReservationNotValidException();
//                }
//            }
//        }
        rezervacija.setTotal(rezervacija.presmetaj());
        return repo.save(rezervacija);
    }

    @Override
    public Optional<Rezervacija> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Rezervacija> rezervaciiZaVozilo(Long voziloId) {
        return repo.rezervaciiZaVozilo(voziloId);
    }


    @Override
    public void deleteById(Long rezervacijaId) {
            repo.deleteById(rezervacijaId);
    }

    @Override
    public List<Rezervacija> rezervaciiZaKorisnik(Long userId) {
        return repo.rezervaciiZaKorisnik(userId);
    }

    @Override
    public double presmetaj(Rezervacija rezervacija) {
         List<Rezervacija> rezervacii = rezervacija.getVozilo().getRezervacii();

        if (rezervacii != null) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            for (Rezervacija r : rezervacii) {
                LocalDate datumOd = LocalDate.parse(r.getDatumOd(),formatter);
                LocalDate datumDo = datumOd.plusDays(r.getDenoviIznajmuvanje());

                LocalDate momentalnaRezDatumOd = LocalDate.parse(rezervacija.getDatumOd(),formatter);
                LocalDate momentalnaRezDatumDo = momentalnaRezDatumOd.plusDays(rezervacija.getDenoviIznajmuvanje());


                if ((datumOd.minusDays(1)).isBefore(momentalnaRezDatumOd) && datumDo.plusDays(1).isAfter(momentalnaRezDatumOd) && r.isUspeshnost()) {
                    throw new ReservationNotValidException();
                }

                if (datumOd.minusDays(1).isBefore(momentalnaRezDatumDo) && datumDo.plusDays(1).isAfter(momentalnaRezDatumDo) && r.isUspeshnost()) {
                    throw new ReservationNotValidException();
                }
            }
        }
        rezervacija.setTotal(rezervacija.presmetaj());
        rezervacija.setUspeshnost(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate momentalnaRezDatumOd = LocalDate.parse(rezervacija.getDatumOd(),formatter);
        LocalDate momentalnaRezDatumDo = momentalnaRezDatumOd.plusDays(rezervacija.getDenoviIznajmuvanje());
        String rezDatumDo = momentalnaRezDatumDo.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        rezervacija.setDatumDo(rezDatumDo);
        repo.save(rezervacija);
        return rezervacija.presmetaj();
    }

    @Override
    public void plati(Rezervacija rezervacija) {
        rezervacija.setPlateno(true);
        repo.save(rezervacija);
    }


}
