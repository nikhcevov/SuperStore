package com.ovchingus.service.func;

import com.ovchingus.persistence.settings.DaoSettings;

public class Settings {

    public static void setSourceMySQL() {
        DaoSettings.setSourceDatabase();
    }

    public static void setSourceCSV() {
        DaoSettings.setSourceFile();
    }

    public static boolean isSourceMySQL() {
        return DaoSettings.isSourceDatabase();
    }

    public static boolean isSourceCSV() {
        return DaoSettings.isSourceFile();
    }
}
