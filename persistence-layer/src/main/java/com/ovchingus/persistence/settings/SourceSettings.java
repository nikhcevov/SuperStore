package com.ovchingus.persistence.settings;

class SourceSettings {

    public static String getSource() {
        return source;
    }

    public static void setSourceDatabase() {
        source = "database";
    }

    public static void setSourceFile() {
        source = "file";
    }

    // by default = database
    private static String source = "database";


}
