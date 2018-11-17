package com.ovchingus.persistence.CSV.dao;

import com.ovchingus.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


abstract class ConnectionCSV<T> implements GenericDao<T, Integer> {

    private static final Logger log = LogManager.getLogger(ConnectionCSV.class);

    abstract void clearFile();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static void createFileIfNotExist(File sourceFile, File outputFile) {
        try {
            if (!sourceFile.exists())
                sourceFile.createNewFile();
            if (!outputFile.exists())
                outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    void clear(String filePath, String tempPath) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
            sourceFile.delete();
            outputFile.renameTo(sourceFile);
            log.info("File " + filePath + " cleared.");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }
}
