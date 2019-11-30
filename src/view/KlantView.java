package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Artikel;
import model.DomainException;
import model.ObserverPattern.Observer;
import view.panels.KlantOverviewPane;

public class KlantView implements Observer {
	private Stage stage = new Stage();
	KlantMainPane borderPane = new KlantMainPane();

	public KlantView() throws DomainException {
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

	@Override
	public void update(Artikel artikel, double uitkomst) {
		borderPane.add(artikel, uitkomst);
	}
}
