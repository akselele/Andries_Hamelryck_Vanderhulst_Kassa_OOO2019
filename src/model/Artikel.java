package model;

/**
 * @author Kasper Vanderhulst
 **/

public class Artikel {
    private String code;
    private String omschrijving;
    private String artikelgroep;
    private double prijs;
    private int voorraad;


    //Dit is een basis artikel klasse met getters en setters met wat constraints.

    public Artikel(String code) {
        setCode(code);
    }

    public Artikel(){

    }

    public Artikel(String code, String omschrijving, String artikelgroep, double prijs, int voorraad) {
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelgroep(artikelgroep);
        setPrijs(prijs);
        setVoorraad(voorraad);
    }

    public void verkoop(int aantalVerkocht){
        if (aantalVerkocht > this.voorraad) {
            throw new IllegalArgumentException("Er zijn niet genoeg artikelen. Nog maar " + this.voorraad + " in stock van " + this.omschrijving + ".");
        }
        this.voorraad = voorraad - aantalVerkocht;
    }

    private void setVoorraad(int voorraad) {
        if (voorraad < 0) {
            throw new IllegalArgumentException("Voorraad mag niet kleiner dan 0 zijn.");
        }
        this.voorraad = voorraad;
    }

    private void setCode(String code) {
        if (code.trim().isEmpty() || code.equalsIgnoreCase(null)) {
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
        if (prijs <= 0) {
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
