package controller.KassaState;

import model.Artikel;
import model.ObserverPattern.EventType;

import java.util.Collections;

/**
 * @Author Noa Andries
 **/
public class EmptyState implements State {
    KassaOverviewController kassaOverviewController;
    public EmptyState(KassaOverviewController kassaOverviewController)
    {
        this.kassaOverviewController = kassaOverviewController;
    }

    @Override
    public void addArtikel(Artikel artikel) {
        kassaOverviewController.getArtikels().add(artikel);
        kassaOverviewController.getArtikels().removeAll(Collections.singleton(null));
        kassaOverviewController.notifyObserver(EventType.KLANTVIEW, kassaOverviewController.getArtikels());
        kassaOverviewController.setState(kassaOverviewController.getActiveState());
    }

}