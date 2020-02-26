package proektwp.proektwp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "komentar")
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;


    private String opis;

    private double rejting;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Vozilo vozilo;

    public Komentar(String opis, double rejting, User user, Vozilo vozilo) {
        this.opis = opis;
        this.rejting = rejting;
        this.user = user;
        this.vozilo = vozilo;
    }

    public Komentar() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getRejting() {
        return rejting;
    }

    public void setRejting(double rejting) {
        this.rejting = rejting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }
}
