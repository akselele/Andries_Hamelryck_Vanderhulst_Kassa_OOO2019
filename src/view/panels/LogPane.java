package view.panels;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import controller.LogController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.ObserverPattern.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Noa Andries
 **/
public class LogPane extends GridPane{
    LogController logController;
    private Label text = new Label();

    public LogPane(LogController logController){
        this.logController = logController;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(text, 0, 0, 1, 1);
    }


    public void update(LocalDateTime now, double uitkomst, double korting) {
        logController.toString(now, uitkomst, korting);
        text.setText(logController.getText());
    }
}
