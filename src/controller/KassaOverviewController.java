package controller;

import controller.KassaState.*;
import database.ArtikelDbContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Kassabonnen.*;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;
import model.WinkelMandje;
import model.kortingen.KassaProperties;
import model.kortingen.KortingContext;
import view.panels.LogPane;
import java.io.IOException;
import java.util.*;

/**
 * @author Noa Andries
 * @author Kasper Vanderhulst
 **/

public class KassaOverviewController implements Subject {
    private State EmptyState;
    private State OnHoldState;
    private State ActiveState;
    private State AfterHoldState;
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
        AfterHoldState = new AfterHoldState(this);
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

    public void setOnHoldState(State onHoldState) {
        OnHoldState = onHoldState;
    }

    public State getActiveState() {
        return ActiveState;
    }

    public State getOnHoldState() {
        return OnHoldState;
    }

    public State getAfterHoldState() {
        return AfterHoldState;
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


//Registers to the list of observers
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

    //Unregisters from the list of observers
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

//returns de uitkomst berekend met korting erbij
    public double getUitkomstKorting() throws IOException {

        double uitkomstMetKorting = 0;
        Map<Artikel,Integer> tempMap = toMap();
        WinkelMandje winkelMandje = new WinkelMandje(tempMap);
        uitkomstMetKorting = kortingContext.getTotaleKorting(winkelMandje);
        return uitkomstMetKorting;
    }

    //returnt de kassabon
    public String getKassabon() throws IOException {

        return kassabonContext.getKassabon(toMap(), getUitkomstKorting(),  uitkomst);

    }


    //returnt de gewone uitkomst zonder korting
    public double getUitkomst() {
        return uitkomst;
    }

    //voegt de prijzen toe aan uitkomst van alle artikels uit de lijst
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

//maakt een map van de lijst van artikels (zodat ook het aantal makkelijk kan bijgehouden worden)
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
