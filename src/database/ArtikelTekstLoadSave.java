package database;

import model.Artikel;
import model.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 @Author Axel Hamelryck
 **/

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate implements LoadSaveStrategy{

    //load in een arraylist om te returnen naar db klasse
    public List<Artikel> load() throws DomainException {
        ArrayList<Artikel> artikels = new ArrayList<>();
        File artikelbestand = new File("src/bestanden/ArtikelText.txt");
        try {
            Scanner sc = new Scanner(artikelbestand);
            while (sc.hasNextLine()) {
                Scanner scLijn = new Scanner(sc.nextLine());
                scLijn.useDelimiter(",");
                String code = scLijn.next();
                String omschrijving = scLijn.next();
                String artikelgroep = scLijn.next();
                double prijs = Double.parseDouble(scLijn.next());
                int voorraad = Integer.parseInt(scLijn.next());
                Artikel artikel = new Artikel(code, omschrijving, artikelgroep, prijs, voorraad);
                artikels.add(artikel);
            }
        } catch (FileNotFoundException ex) {
            throw new DomainException("Fout bij het inlezen", ex);
        }
//        for(Artikel artikel: artikels){
//            System.out.println(artikel.toString());
//        }
        return artikels;
    }


    //Het saven in het HUIDIG bestand dat men ook gebruikt om te lezen.
    //Indien we deze methode willen testen kunnen we gewoon de naam van het uitvoerbestand veranderen.
    public void save(List<Artikel> artikels) throws DomainException {
        File artikelbestand = new File("src/bestanden/ArtikelText.txt");

        try{
            PrintWriter writer = new PrintWriter(artikelbestand);
            for(Artikel artikel: artikels){
                writer.println(artikel.getCode() + "," + artikel.getOmschrijving() + ","
                        + artikel.getArtikelgroep() + "," + artikel.getPrijs() +","+ artikel.getVoorraad());
            }
            writer.close();
        }catch (FileNotFoundException e){
            throw new DomainException("Fout bij het uitschrijven van het bestand", e);
        }
    }
}