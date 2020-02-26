package proektwp.proektwp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String ime;

    private String prezime;

    private String adresa;

    private int godini;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Rezervacija> rezervacii;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Komentar> komentari;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getGodini() {
        return godini;
    }

    public void setGodini(int godini) {
        this.godini = godini;
    }

    public List<Rezervacija> getRezervacii() {
        return rezervacii;
    }

    public void setRezervacii(List<Rezervacija> rezervacii) {
        this.rezervacii = rezervacii;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public User(String ime, String prezime, String adresa, int godini, List<Rezervacija> rezervacii, List<Komentar> komentari) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.godini = godini;
        this.rezervacii = rezervacii;
        this.komentari = komentari;
    }

    public User(){}

    public User(String ime, String prezime, String adresa, int godini) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.godini = godini;
        this.rezervacii = null;
        this.komentari = null;
    }


}
