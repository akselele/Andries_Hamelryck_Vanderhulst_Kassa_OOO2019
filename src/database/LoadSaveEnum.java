package database;

import database.excel.ArtikelExcelLoadSaveStrategy;

/**
 @Author Noa Andries
 **/

public enum LoadSaveEnum {
    EXCEL(ArtikelExcelLoadSaveStrategy.class),
    TEKST(ArtikelTekstLoadSave.class);

    private Class klasse;

    LoadSaveEnum(Class a) {
        this.klasse = a;
    }

    public Class getKlasse() {
        return klasse;
    }
}