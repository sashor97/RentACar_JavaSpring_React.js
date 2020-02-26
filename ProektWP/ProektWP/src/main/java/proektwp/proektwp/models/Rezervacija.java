package proektwp.proektwp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String datumOd;

    private String datumDo;


    private int denoviIznajmuvanje;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonSerialize(using = LocalDateSerializer.class)

    private boolean uspeshnost;

    private boolean plateno;



    private double total;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Vozilo vozilo;


    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }



    public Rezervacija(String datumOd, int denoviIznajmuvanje, User user, Vozilo vozilo) {
        this.datumOd = datumOd;
        this.denoviIznajmuvanje = denoviIznajmuvanje;
        this.user = user;
        this.vozilo = vozilo;
        this.total = vozilo.getCenaPoDen() * denoviIznajmuvanje * 1.0;
        this.uspeshnost = false;
        this.plateno = false;
        this.datumDo = null;
    }


    public Rezervacija(String datumOd, int denoviIznajmuvanje) {
        this.datumOd = datumOd;
        this.denoviIznajmuvanje = denoviIznajmuvanje;
        this.user = null;
        this.vozilo = null;
        this.total = 0;
        this.uspeshnost = false;
        this.datumDo = null;
        this.plateno = false;
    }


    public Rezervacija(){
        this.total = 0;
        this.uspeshnost = false;
        this.datumDo = null;
        this.plateno = false;
    }

    public boolean isPlateno() {
        return plateno;
    }

    public void setPlateno(boolean plateno) {
        this.plateno = plateno;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public int getDenoviIznajmuvanje() {
        return denoviIznajmuvanje;
    }

    public void setDenoviIznajmuvanje(int denoviIznajmuvanje) {
        this.denoviIznajmuvanje = denoviIznajmuvanje;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public boolean isUspeshnost() {
        return uspeshnost;
    }

    public void setUspeshnost(boolean uspeshnost) {
        this.uspeshnost = uspeshnost;
    }

    public double presmetaj(){
        if (vozilo != null)
        return vozilo.getCenaPoDen() * denoviIznajmuvanje * 1.0;
        else
            return 0;
    }

}
