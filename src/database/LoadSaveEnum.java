package database;

import database.excel.ArtikelExcelLoadSaveStrategy;

/**
 * @Author Noa Andries
 **/

//TODO AANMAAK PAS BIJ KLIK

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
