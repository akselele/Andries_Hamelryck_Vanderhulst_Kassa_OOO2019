package controller.KassaState;

import model.Artikel;

import java.io.IOException;

/**
 * @author Noa Andries
 **/

public interface State {

    default void addArtikel(Artikel artikel) {
        throw new IllegalArgumentException("Artikel toevoegen gaat niet");
    }

    default void verkoop() throws IOException {
        throw new IllegalArgumentException("Afrekenen gaat niet");
    }

    default void cancel() {
        throw new IllegalArgumentException("cancellen gaat niet");
    }

    default void handleDelete(Artikel artikel) {
        throw new IllegalArgumentException("Artikel verwijderen gaat niet");
    }

    default void handleHold() {
        throw new IllegalArgumentException("On Hold zetten gaat niet");
    }
}
