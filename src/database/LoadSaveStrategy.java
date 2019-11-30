package database;

import model.Artikel;
import model.DomainException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 @Author Noa Andries
 **/
public interface LoadSaveStrategy {

    List<Artikel> load() throws DomainException;

    void save(List<Artikel> artikels) throws DomainException;
}
