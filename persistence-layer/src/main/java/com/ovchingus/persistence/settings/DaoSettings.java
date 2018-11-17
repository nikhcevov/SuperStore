package com.ovchingus.persistence.settings;

public class DaoSettings {

    private static int cleanerCounter = 10;

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

    public static String getCsvFilePath() {
        return csvFilePath;
    }

    public static void setCsvFilePath(String csvFilePath) {
        DaoSettings.csvFilePath = csvFilePath;
    }

    private static String csvFilePath = ".\\persistence-layer\\src\\main\\java\\com\\ovchingus\\persistence\\csv\\data\\";


    public static int getCleanFileAfterNumberOfOperations() {
        return cleanerCounter;
    }

    public static void setCleanFileAfterNumberOfOperations(int cleanerCounter) {
        DaoSettings.cleanerCounter = cleanerCounter;
    }

}
