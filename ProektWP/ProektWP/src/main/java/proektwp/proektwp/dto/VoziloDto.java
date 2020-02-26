package proektwp.proektwp.dto;

public class VoziloDto {

//    model: this.modelInput.value,
//    imgUrl: this.urlInput.value ,
//    lokacija: this.lokacijaInput.value ,
//    cenaPoDen: this.cenaInput.value,
//    proizvoditel: this.proizvoditelInput.value,
//    sopstvenik: this.sopstvenikInput.value,
//    kategorija: this.kategorijaInput.value
    String model;
    String imgUrl;
    String lokacija;
    Long cenaPoDen;
    Long proizvoditel;
    Long sopstvenik;
    Long kategorija;

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

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Long getCenaPoDen() {
        return cenaPoDen;
    }

    public void setCenaPoDen(Long cenaPoDen) {
        this.cenaPoDen = cenaPoDen;
    }

    public Long getProizvoditel() {
        return proizvoditel;
    }

    public void setProizvoditel(Long proizvoditel) {
        this.proizvoditel = proizvoditel;
    }

    public Long getSopstvenik() {
        return sopstvenik;
    }

    public void setSopstvenik(Long sopstvenik) {
        this.sopstvenik = sopstvenik;
    }

    public Long getKategorija() {
        return kategorija;
    }

    public void setKategorija(Long kategorija) {
        this.kategorija = kategorija;
    }
}
