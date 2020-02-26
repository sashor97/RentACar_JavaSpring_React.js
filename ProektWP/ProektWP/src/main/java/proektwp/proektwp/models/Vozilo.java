package proektwp.proektwp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vozilo")
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String model;

    private String imgUrl;

    private String lokacija;

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    private double cenaPoDen;


    @JsonIgnore
    @ManyToOne
    private Proizvoditel proizvoditel;

    @JsonIgnore
    @ManyToOne
    private Kategorija kategorija;

    @JsonIgnore
    @ManyToOne
    private Sopstvenik sopstvenik;


    @OneToMany(mappedBy = "vozilo", cascade = CascadeType.REMOVE)
    private List<Rezervacija> rezervacii;

    @OneToMany(mappedBy = "vozilo", cascade = CascadeType.REMOVE)
    private List<Komentar> komentari;

    public Vozilo(String model, String imgUrl, String lokacija, double cenaPoDen, Proizvoditel proizvoditel, Kategorija kategorija, Sopstvenik sopstvenik, List<Rezervacija> rezervacii, List<Komentar> komentari) {
        this.model = model;
        this.imgUrl = imgUrl;
        this.lokacija = lokacija;
        this.cenaPoDen = cenaPoDen;
        this.proizvoditel = proizvoditel;
        this.kategorija = kategorija;
        this.sopstvenik = sopstvenik;
        this.rezervacii = rezervacii;
        this.komentari = komentari;
    }

    public Vozilo(String model, String imgUrl, String lokacija, double cenaPoDen, Proizvoditel proizvoditel, Kategorija kategorija, Sopstvenik sopstvenik) {
        this.model = model;
        this.imgUrl = imgUrl;
        this.lokacija = lokacija;
        this.cenaPoDen = cenaPoDen;
        this.proizvoditel = proizvoditel;
        this.kategorija = kategorija;
        this.sopstvenik = sopstvenik;
        this.rezervacii = null;
        this.komentari = null;
    }
    public Vozilo(){
        this.rezervacii = null;
        this.komentari = null;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getCenaPoDen() {
        return cenaPoDen;
    }

    public void setCenaPoDen(double cenaPoDen) {
        this.cenaPoDen = cenaPoDen;
    }

    public Proizvoditel getProizvoditel() {
        return proizvoditel;
    }

    public void setProizvoditel(Proizvoditel proizvoditel) {
        this.proizvoditel = proizvoditel;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Sopstvenik getSopstvenik() {
        return sopstvenik;
    }

    public void setSopstvenik(Sopstvenik sopstvenik) {
        this.sopstvenik = sopstvenik;
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
}
