package application;
	
import controller.LogController;
import database.ArtikelDbInMemory;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DomainException;
import model.ObserverPattern.EventType;
import view.KassaView;
import view.KlantView;

import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();

		kassaView.registerObserver(EventType.KLANTVIEW, klantView);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
