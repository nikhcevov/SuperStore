package com.ovchingus.persistence.csv.dao;

import com.ovchingus.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


abstract class ConnectionCSV<T> implements GenericDao<T, Integer> {

    private static final Logger log = LogManager.getLogger(ConnectionCSV.class);

    abstract void clearFile();

    boolean deleteAll(String filePath, String tempPath) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);
        boolean a = false, b = false;
        try {
            if (!sourceFile.exists())
                a = sourceFile.createNewFile();
            if (!outputFile.exists())
                b = outputFile.createNewFile();
            return a && b
                    && sourceFile.delete()
                    && outputFile.renameTo(sourceFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        } finally {
            clearFile();
        }
    }

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
            if (sourceFile.delete() && outputFile.renameTo(sourceFile))
                log.info("File " + filePath + " cleared.");
            else log.error("File " + filePath + " wasn`t cleared.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
