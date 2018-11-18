package com.ovchingus.persistence.settings;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class DaoSettings {

    private static Parameters params = new Parameters();

    private static XMLConfiguration config;

    private static FileBasedConfigurationBuilder<XMLConfiguration> builder =
            new FileBasedConfigurationBuilder<>(XMLConfiguration.class)
                    .configure(params.xml().setFileName("./dao.cfg.xml")
                            .setValidating(true));

    static {
        try {
            config = builder.getConfiguration();
            builder.setAutoSave(true);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void setSourceMySQL() {
        try {
            config.setProperty("source.current", "database");
            builder.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSourceMySQL() {
        return config.getString("source.current").equalsIgnoreCase("database");
    }

    public static void setSourceCSV() {
        try {
            config.setProperty("source.current", "csv");
            builder.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSourceCSV() {
        return config.getString("source.current").equalsIgnoreCase("csv");
    }

    public static String getCsvFilePath() {
        return config.getString("csv.path");
    }

    public static void setCsvFilePath(String csvFilePath) {
        try {
            config.setProperty("csv.path", csvFilePath);
            builder.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static int getCleanFileAfterNumberOfOperations() {
        return config.getInt("csv.cleaner.steps");
    }

    public static void setCleanFileAfterNumberOfOperations(int cleanerCounter) {
        try {
            config.setProperty("csv.cleaner.steps", cleanerCounter);
            builder.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

}