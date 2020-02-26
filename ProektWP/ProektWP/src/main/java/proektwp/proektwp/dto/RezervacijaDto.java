package proektwp.proektwp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import proektwp.proektwp.models.User;
import proektwp.proektwp.models.Vozilo;

import javax.persistence.ManyToOne;

public class RezervacijaDto {
    String datumOd;

    String datumDo;

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    int denoviIznajmuvanje;


    Long user;
    Long vozilo;

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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getVozilo() {
        return vozilo;
    }

    public void setVozilo(Long vozilo) {
        this.vozilo = vozilo;
    }
}
