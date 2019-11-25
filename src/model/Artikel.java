package model;

public class Artikel {
    private String code;
    private String omschrijving;
    private String artikelgroep;
    private double prijs;
    private int voorraad;

    public Artikel(){

    }

    public Artikel(String code,String omschrijving, String artikelgroep, double prijs, int voorraad){
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelgroep(artikelgroep);
        setPrijs(prijs);
        setVoorraad(voorraad);
    }

    private void setVoorraad(int voorraad){
        if(voorraad < 0){
            throw new IllegalArgumentException("Voorraad mag niet kleiner dan 0 zijn.");
        }
        this.voorraad = voorraad;
    }

    private void setCode(String code) {
        if(code.trim().isEmpty() || code.equalsIgnoreCase(null)){
            throw new IllegalArgumentException("Artikelcode mag niet leeg zijn.");
        }
        this.code = code;
    }

    private void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    private void setArtikelgroep(String artikelgroep) {
        this.artikelgroep = artikelgroep;
    }

    private void setPrijs(double prijs) {
        if(prijs <= 0){
            throw new IllegalArgumentException("Artikelprijs mag niet kleiner dan 0 zijn.");
        }
        this.prijs = prijs;
    }

    public String getCode() {
        return code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getArtikelgroep() {
        return artikelgroep;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    @Override
    public String toString() {
        return code + " - " + omschrijving + " - $" + prijs + " - " + artikelgroep + " - voorraad: " + voorraad;
    }
}
