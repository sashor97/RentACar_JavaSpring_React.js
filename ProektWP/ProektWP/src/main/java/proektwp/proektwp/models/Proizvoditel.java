package proektwp.proektwp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="proizvoditel")
public class Proizvoditel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;

    @OneToMany(mappedBy = "proizvoditel", cascade = CascadeType.REMOVE)
    public List<Vozilo> vozila;

    public Proizvoditel(String name, List<Vozilo> vozila) {
        this.name = name;
        this.vozila = vozila;
    }

    public Proizvoditel(String name) {
        this.name = name;
    }

    public Proizvoditel(){

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
