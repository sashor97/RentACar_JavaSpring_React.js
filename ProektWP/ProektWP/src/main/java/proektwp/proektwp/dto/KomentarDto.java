package proektwp.proektwp.dto;

public class KomentarDto {
    String opis;
    String rejting;

    Long user;
    Long vozilo;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getRejting() {
        return rejting;
    }

    public void setRejting(String rejting) {
        this.rejting = rejting;
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
