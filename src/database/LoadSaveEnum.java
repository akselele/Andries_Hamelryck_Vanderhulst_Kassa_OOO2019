package database;

import database.excel.ArtikelExcelLoadSaveStrategy;

/**
 * @author Noa Andries
 **/


public enum LoadSaveEnum {
    EXCEL(ArtikelExcelLoadSaveStrategy.class),
    TEXT(ArtikelTekstLoadSave.class);

    private Class klasse;

    LoadSaveEnum(Class a) {
        this.klasse = a;
    }

    public Class getKlasse() {
        return klasse;
    }
}
