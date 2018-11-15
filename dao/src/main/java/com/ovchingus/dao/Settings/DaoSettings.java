package com.ovchingus.dao.Settings;

public class DaoSettings {

    public static void setSourceDatabase() {
        SourceSettings.setSourceDatabase();
    }

    public static boolean isSourceDatabase() {
        return SourceSettings.getSource().equalsIgnoreCase("database");
    }

    public static void setSourceFile() {
        SourceSettings.setSourceFile();
    }

    public static boolean isSourceFile() {
        return SourceSettings.getSource().equalsIgnoreCase("file");
    }


}
