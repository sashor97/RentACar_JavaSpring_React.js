package proektwp.proektwp.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sopstvenik")
public class Sopstvenik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String ime;

    private String prezime;

    private String adresa;

    private int godini;

    @OneToMany(mappedBy = "sopstvenik", cascade = CascadeType.REMOVE)
    private List<Vozilo> vozila;

    public Sopstvenik(String ime, String prezime, String adresa, int godini, List<Vozilo> vozila) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.godini = godini;
        this.vozila = vozila;
    }

    public Sopstvenik(){

    }

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

    public List<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(List<Vozilo> vozila) {
        this.vozila = vozila;
    }
}
