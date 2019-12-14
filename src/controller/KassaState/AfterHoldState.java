package controller.KassaState;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.ObserverPattern.EventType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
/**
 * @Author Noa Andries
 **/
public class AfterHoldState implements State{
    KassaOverviewController kassaOverviewController;
    public AfterHoldState(KassaOverviewController kassaOverviewController)
    {
        this.kassaOverviewController = kassaOverviewController;
    }

    @Override
    public void addArtikel(Artikel artikel) {
        kassaOverviewController.getArtikels().add(artikel);
        kassaOverviewController.getArtikels().removeAll(Collections.singleton(null));
        kassaOverviewController.notifyObserver(EventType.KLANTVIEW, kassaOverviewController.getArtikels());
    }

    @Override
    public void verkoop() throws IOException {
        Map<Artikel, Integer> artikelIntegerMap = kassaOverviewController.toMap();
        ObservableList<Artikel> aObservable = FXCollections.observableArrayList();
        ArrayList<Artikel> a = new ArrayList<Artikel>(kassaOverviewController.getArtikelDbContext().getArtikels().values());
        Iterator<Artikel> iter = a.iterator();
        Iterator<Artikel> iter2 = artikelIntegerMap.keySet().iterator();
        //Eerst loopen over kassalist en dan voor elke entry in kassalist de stock verminderen in de algemene Map
        for(Artikel artikel : artikelIntegerMap.keySet()){
            kassaOverviewController.getArtikelDbContext().stockAanpas(artikel, artikelIntegerMap.get(artikel));
        }
        aObservable.addAll(a);
        kassaOverviewController.notifyObserver(EventType.KASSAVIEW, aObservable);
        kassaOverviewController.getLogPane().update(LocalDateTime.now(), kassaOverviewController.getUitkomst(), kassaOverviewController.getUitkomstKorting());
        System.out.println(kassaOverviewController.getKassabon());
        kassaOverviewController.getArtikels().clear();
        kassaOverviewController.notifyObserver(EventType.KLANTVIEW, kassaOverviewController.getArtikels());
        kassaOverviewController.getArtikelDbContext().save(a);
        kassaOverviewController.setState(kassaOverviewController.getEmptyState());
    }

    @Override
    public void cancel() {
        kassaOverviewController.getArtikels().clear();
        kassaOverviewController.notifyObserver(EventType.KLANTVIEW, kassaOverviewController.getArtikels());
        kassaOverviewController.setState(kassaOverviewController.getEmptyState());
    }

    @Override
    public void handleDelete(Artikel artikel) {
        kassaOverviewController.getArtikels().remove(artikel);
        kassaOverviewController.notifyObserver(EventType.KLANTVIEW, kassaOverviewController.getArtikels());
    }


}
