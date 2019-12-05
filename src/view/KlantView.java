package view;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Artikel;
import model.DomainException;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;

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
	public void update(ObservableList<Artikel> artikels) {
		borderPane.add(artikels);
	}
}
