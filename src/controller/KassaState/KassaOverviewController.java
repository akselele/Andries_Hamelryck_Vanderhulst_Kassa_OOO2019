package controller.KassaState;

import database.ArtikelDbContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.Artikel;
import model.DomainException;
import model.Kassabonnen.*;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;
import model.WinkelMandje;
import model.kortingen.KassaProperties;
import model.kortingen.KortingContext;
import model.kortingen.KortingFactory;
import view.panels.LogPane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Noa Andries
 **/

public class KassaOverviewController implements Subject {
    private State EmptyState;
    private State OnHoldState;
    private State ActiveState;
    private State state;
    private ArtikelDbContext artikelDbContext;
    private ObservableList<Artikel> artikels;
    private ObservableList<Artikel> artikelsHold;
    private double uitkomst;
    private double uitkomstHold;
    private Map<EventType, List<Observer>> observers;
    private LogPane logPane;
    private Properties properties;
    private KortingContext kortingContext;
    private KassabonContext kassabonContext;

    public KassaOverviewController(ArtikelDbContext artikelDbContext, LogPane logPane) throws IOException {
        OnHoldState = new OnHoldState(this);
        ActiveState = new ActiveState(this);
        EmptyState = new EmptyState(this);
        state = getEmptyState();
        this.logPane = logPane;
        observers = new HashMap<>();
        this.artikelDbContext = artikelDbContext;
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());
        artikelsHold = FXCollections.observableArrayList(new ArrayList<Artikel>());
        properties = KassaProperties.load();
        kortingContext = new KortingContext();
        kassabonContext = new KassabonContext();
    }

    public State getActiveState() {
        return ActiveState;
    }

    public State getOnHoldState() {
        return OnHoldState;
    }

    public State getState() {
        return state;
    }

    public State getEmptyState() {
        return EmptyState;
    }

    public void addArtikel(Artikel artikel){
            state.addArtikel(artikel);
    }

    public void setState(State state) {
        this.state = state;
    }

    public ObservableList<Artikel> getArtikelsHold() {
        return artikelsHold;
    }


    public Artikel getArtikel(String code){
        return artikelDbContext.getArtikel(code);
    }

    public ObservableList<Artikel> getArtikels() {
        return artikels;
    }

    public double getUitkomstHold() {
        return uitkomstHold;
    }

    public void setUitkomst(double uitkomst) {
        this.uitkomst = uitkomst;
    }

    public void setUitkomstHold(double uitkomstHold) {
        this.uitkomstHold = uitkomstHold;
    }

    @Override
    public void registerObserver(EventType e, Observer o) {
        if (observers.get(e) == null){
            List<Observer> observers = new ArrayList<>();
            observers.add(o);
            this.observers.put(e,observers);
        }else{
            List<Observer> observers = this.observers.get(e);
            observers.add(o);
        }
    }

    @Override
    public void unregisterObserver(EventType e, Observer o) {
        this.observers.get(e).remove(o);
    }

    @Override
    public void notifyObserver(EventType e, ObservableList<Artikel> artikels) {
        for (Observer o:this.observers.get(e)) {
            o.update(artikels);
        }
    }

    @Override
    public void notifyObserver(EventType e, double uitkomst) {
        for (Observer o:this.observers.get(e)) {
            o.update(uitkomst);
        }
    }

    public void verkoop() throws IOException {
        state.verkoop();
    }

    public void cancel(){
        state.cancel();
    }

    public double getUitkomstKorting() throws IOException {

        double uitkomstMetKorting = 0.0;

        Map<Artikel,Integer> tempMap = toMap();
        WinkelMandje winkelMandje = new WinkelMandje(tempMap);

//todo lijst niet gehardcode maar via enum?
        String[] kortingen = kortingContext.getKortingList();
        KortingFactory kortingFactory = new KortingFactory();
        properties = KassaProperties.load();


        if (properties.getProperty("GROEPKORTING").equalsIgnoreCase("true")) {
            kortingContext.setKortingStrategy(kortingFactory.createKorting(kortingen[0]));
            uitkomstMetKorting = kortingContext.getTotaleKorting(winkelMandje);
        }
        if (properties.getProperty("DREMPELKORTING").equalsIgnoreCase("true")) {
            kortingContext.setKortingStrategy(kortingFactory.createKorting(kortingen[1]));
            uitkomstMetKorting = kortingContext.getTotaleKorting(winkelMandje);
        }
        if (properties.getProperty("DUURSTEITEMKORTING").equalsIgnoreCase("true")) {
            kortingContext.setKortingStrategy(kortingFactory.createKorting(kortingen[2]));
            uitkomstMetKorting = kortingContext.getTotaleKorting(winkelMandje);
        }

        return uitkomstMetKorting;
    }

    public String getKassabon() throws IOException {

        String x = "";
//todo lijst niet gehardcode maar via enum?
        String[] kassabonnen = kassabonContext.getKassabonList();
        KassabonFactory kassabonFactory = new KassabonFactory();
        properties = KassabonProperties.load();


        if (properties.getProperty("HEADER").equalsIgnoreCase("true")) {
            kassabonContext.setKassabon(kassabonFactory.createKassabon(kassabonnen[0]));
            x = kassabonContext.getKassabon(toMap(), getUitkomstKorting(),  uitkomst, properties.getProperty("HEADERTEXT"));
        }
        if (properties.getProperty("FOOTER").equalsIgnoreCase("true")) {
            kassabonContext.setKassabon(kassabonFactory.createKassabon(kassabonnen[1]));
            x = kassabonContext.getKassabon(toMap(), getUitkomstKorting(),  uitkomst, "");
        }
        if (properties.getProperty("BEIDE").equalsIgnoreCase("true")) {
            kassabonContext.setKassabon(kassabonFactory.createKassabon(kassabonnen[2]));
            x= kassabonContext.getKassabon(toMap(), getUitkomstKorting(),  uitkomst,  properties.getProperty("HEADERTEXT"));
        }
        if(properties.getProperty("BEIDE").equalsIgnoreCase("false")  && properties.getProperty("FOOTER").equalsIgnoreCase("false")
         && properties.getProperty("HEADER").equalsIgnoreCase("false")){
            Kassabon kassabon = new BasisKassabon();
            x = kassabon.string(toMap(), getUitkomstKorting(),uitkomst, "" );
        }
        return x;
    }


    public double getUitkomst() {
        return uitkomst;
    }

    public void uitkomst(){
        uitkomst = 0;
        for(Artikel artikel : artikels){
            uitkomst += artikel.getPrijs();
        }
    }

    public void handleDelete(Artikel artikel) {
               state.handleDelete(artikel);
    }

    public void handleHold() {
        state.handleHold();
    }

    public void handleAfhandel() {
        notifyObserver(EventType.KLANTVIEW, uitkomst);
    }

    //TODO Hier moet de korting ook nog bij.
    public Map<Artikel,Integer> toMap(){
        Map<Artikel, Integer> artikelsMap = new HashMap<>();
        artikelsMap.clear();
        int ammount = 1;
        for (Artikel artikel : artikels) {
            ammount = Collections.frequency(artikels, artikel);
            artikelsMap.put(artikel, ammount);
        }

        return artikelsMap;
    }

    public ArtikelDbContext getArtikelDbContext() {
        return artikelDbContext;
    }

    public LogPane getLogPane() {
        return logPane;
    }
}
