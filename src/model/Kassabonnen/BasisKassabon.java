package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

public class BasisKassabon implements Kassabon {

    public BasisKassabon(){

    }


    @Override
    public void string(Map<Artikel, Integer> artikelIntegerMap, double uitkomst) {
        String y = "";
        String x = "";
        for(Artikel artikel : artikelIntegerMap.keySet()){
            x += artikel.getOmschrijving() + "\t\t        " + artikelIntegerMap.get(artikel) + "\t" + artikel.getPrijs() + "\t\n";
        }
        y= "Omschrijving\t\tAantal\t  Prijs\n" +
                "*****************************\n" +
                x +
                "*****************************\n" +
                "Betaald (inclusief korting) : " + uitkomst + "€         \n";
        System.out.println(y);
    }
}
