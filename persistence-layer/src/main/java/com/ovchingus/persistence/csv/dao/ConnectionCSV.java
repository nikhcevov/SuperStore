package com.ovchingus.persistence.csv.dao;

import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.settings.DaoSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


abstract class ConnectionCSV<T> implements GenericDao<T, Integer> {

    private static final Logger log = LogManager.getLogger(ConnectionCSV.class);
    private int clearAfter = DaoSettings.getCleanFileAfterNumberOfOperations();
    String filePath = DaoSettings.getCsvFilePath();
    String tempPath = DaoSettings.getCsvFilePath();
    private int clearedTimes = 0;


    void clearFile() {
        clearedTimes++;
        if (clearedTimes >= clearAfter && clearAfter != 0) {
            clear(filePath, tempPath);
            clearedTimes = 0;
        }
    }


    boolean initialize(String filePath, String tempPath) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);
        boolean a = true;
        boolean b = true;
        try {
            if (!sourceFile.exists()) {
                a = sourceFile.createNewFile();
                log.info("File " + filePath + " created.");
            }

            if (!outputFile.exists()) {
                b = outputFile.createNewFile();
                log.info("File " + tempPath + " created.");
            }

            if (!a || !b)
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    boolean deleteAll(String filePath, String tempPath) {
        if (initialize(filePath, tempPath)) {
            File sourceFile = new File(filePath);
            File outputFile = new File(tempPath);
            try {
                if (sourceFile.delete()
                        && outputFile.renameTo(sourceFile)) {
                    log.info("DeleteAll in " + filePath + " successfully");
                    return true;
                } else return false;
            } finally {
                clearFile();
            }
        } else {
            log.error("Delete all " + filePath + " rejected. Initialize error");
            return false;
        }
    }

    private void clear(String filePath, String tempPath) {
        if (initialize(filePath, tempPath)) {
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
                //reader.close();
                //writer.close();
                if (sourceFile.delete() && outputFile.renameTo(sourceFile))
                    log.info("File " + filePath + " cleared.");
                else log.error("File " + filePath + " wasn`t cleared.");
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } else log.error("File " + filePath + " wasn`t cleared. Initialize error");
    }

    @Override
    public void closeCurrentSession() {
    }

    @Override
    public void openCurrentSession() {
    }
}
