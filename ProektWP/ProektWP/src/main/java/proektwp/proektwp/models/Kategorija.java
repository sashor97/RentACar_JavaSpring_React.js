package proektwp.proektwp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kategorija")
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;

    @OneToMany(mappedBy = "kategorija", cascade = CascadeType.REMOVE)
    private List<Vozilo> vozila;

    public Kategorija(String name, List<Vozilo> vozila) {
        this.name = name;
        this.vozila = vozila;
    }

    public Kategorija(String name) {
        this.name = name;

    }

    public Kategorija(){

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(List<Vozilo> vozila) {
        this.vozila = vozila;
    }
}
