package com.ovchingus.persistence.settings;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class DaoSettings {

    private static Configurations configs = new Configurations();

    private static XMLConfiguration config;

    private static FileBasedConfigurationBuilder<XMLConfiguration> builder;

    static {
        try {
            builder = configs.xmlBuilder("./dao.cfg.xml");
            config = builder.getConfiguration();
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
