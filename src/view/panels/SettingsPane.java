package view.panels;

import database.ArtikelDbContext;
import database.ArtikelDbInMemory;
import database.LoadSaveFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.DomainException;
import model.Kassabonnen.KassabonContext;
import model.Kassabonnen.KassabonFactory;
import model.kortingen.KassaProperties;
import model.kortingen.KortingContext;
import model.kortingen.KortingFactory;

import javax.tools.Tool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Kasper Vanderhulst
 * @author Noa Andries
 **/


public class SettingsPane extends GridPane {
    ArtikelDbContext artikelDbContext = new ArtikelDbContext();
    LoadSaveFactory loadSaveFactory = LoadSaveFactory.getInstance();
    KortingContext kortingContext = new KortingContext();
    KortingFactory kortingFactory = KortingFactory.getInstance();
    KassabonContext kassabonContext = new KassabonContext();
    KassabonFactory kassabonFactory = KassabonFactory.getInstance();
    Properties properties = KassaProperties.load();


    public SettingsPane() throws IOException {
        VBox vBox = new VBox();
        HBox hBoxloadsave = new HBox();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


        String[] vertaalLijst = artikelDbContext.getStrategiesList();

        for (String s : vertaalLijst) {
            Button b = new Button(s);
            hBoxloadsave.getChildren().add(b);

            b.setOnAction(event -> {
                try {
                    artikelDbContext.setLoadSaveStrategy(loadSaveFactory.getStrategy(s), s.toLowerCase());

                } catch (IOException e) {
                    displayErrorMessage("Fout bij het laden.");
                }
            });

        }

        vBox.getChildren().add(hBoxloadsave);


        HBox hboxKorting2 = new HBox();
        String[] kortingLijst2 = new KortingContext().getKortingList();
        ToggleGroup toggleGroupKorting = new ToggleGroup();
        hboxKorting2.setPadding(new Insets(5,5,5,5));


        for (String s : kortingLijst2) {

            //new box for each strategy
            HBox hBoxKortingStrategy;

            //each strategy gets a radiobutton for the choice and then a slider for the korting ammount and a extra field (for group or for drempelwaarde)

            RadioButton kortingStrChoice = new RadioButton(s);
            kortingStrChoice.setToggleGroup(toggleGroupKorting);
            if(properties.getProperty(s).equalsIgnoreCase("TRUE")){
                kortingStrChoice.fire();
            }




            Slider kortingsAmmountSlider = new Slider(0,100,Double.parseDouble(properties.getProperty(s+"WAARDE")));
            Label sliderlabel = new Label();
            sliderlabel.textProperty().setValue(properties.getProperty(s+"WAARDE"));
            kortingsAmmountSlider.setShowTickLabels(true);
            kortingsAmmountSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    sliderlabel.textProperty().setValue(String.valueOf(newValue.intValue()));
                }
            });


            TextField extraParam = new TextField();
            if(s.equalsIgnoreCase("DREMPELKORTING")){
                extraParam.setText(properties.getProperty("DREMPELWAARDE"));
            }else if(s.equalsIgnoreCase("GROEPKORTING")){
                extraParam.setText(properties.getProperty("GROEP"));
            }else extraParam.setVisible(false);


            /*Tooltip tooltip = new Tooltip("test");
            extraParam.setTooltip(tooltip);*/



            Button confirm = new Button("Save");

            hBoxKortingStrategy = new HBox(kortingStrChoice,kortingsAmmountSlider,sliderlabel,extraParam,confirm);
            vBox.getChildren().add(hBoxKortingStrategy);

            confirm.setOnAction(event -> {
                try {
                    kortingContext.setKortingStrategyProperties(kortingFactory.createKorting(s), Integer.parseInt(sliderlabel.getText()), extraParam.getCharacters().toString());
                } catch (Exception e) {
                    displayErrorMessage("Fout bij het bepalen van de korting");
                    e.printStackTrace();
                }
            });
            hBoxKortingStrategy.setPadding(new Insets(10,10,10,10));
        }

        vBox.getChildren().add(hboxKorting2);

        HBox hboxKassabon = new HBox();
        String[] kassabonLijst = kassabonContext.getKassabonList();

        for (String s : kassabonLijst) {
            Button b = new Button(s);
            hboxKassabon.getChildren().add(b);

            b.setOnAction(event -> {
                try {
                    kassabonContext.setKassabonProperties(kassabonFactory.createKassabon(s));
                } catch (Exception e) {
                    displayErrorMessage("Fout bij het bepalen van de kassabon");
                    e.printStackTrace();
                }
            });
        }
        vBox.getChildren().add(hboxKassabon);

        this.getChildren().addAll(vBox);
    }


    public void displayErrorMessage(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }
}
