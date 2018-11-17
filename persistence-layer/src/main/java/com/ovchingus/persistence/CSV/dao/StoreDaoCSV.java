package com.ovchingus.persistence.CSV.dao;

import com.ovchingus.persistence.CSV.entities.StoreEntityCSV;
import com.ovchingus.persistence.settings.DaoSettings;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoCSV extends ConnectionCSV<StoreEntityCSV> {

    private String filePath = DaoSettings.getCsvFilePath() + "stores.csv";
    private String tempPath = DaoSettings.getCsvFilePath() + "tempStores.csv";
    private static final Logger log = LogManager.getLogger(StoreDaoCSV.class);

    private int clearAfter = DaoSettings.getCleanFileAfterNumberOfOperations();
    private int clearedTimes = 0;

    public void clearFile() {
        clearedTimes++;
        if (clearedTimes >= clearAfter) {
            clear(filePath, tempPath);
            clearedTimes = 0;
        }
    }

    @Override
    public void persist(StoreEntityCSV entity) {
        try {
            FileUtils.writeStringToFile(new File(filePath),
                    (entity.getId() + "," + entity.getName() + "," + entity.getAddress() + "\n"),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        clearFile();
    }

    @Override
    public void update(StoreEntityCSV entity) {
        StoreEntityCSV e = findById(entity.getId());
        delete(e);
        persist(entity);
        clearFile();
    }

    @Override
    public StoreEntityCSV findById(Integer id) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                Integer curId = Integer.parseInt(record.get(0));
                if (curId.equals(id))
                    return new StoreEntityCSV(Integer.parseInt(record.get(0)),
                            record.get(1), record.get(2));
            }
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void delete(StoreEntityCSV entity) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            String outputLine = entity.getId() + "," + entity.getName()
                    + "," + entity.getAddress();
            while ((line = reader.readLine()) != null) {
                if (!line.equals(outputLine)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
            sourceFile.delete();
            outputFile.renameTo(sourceFile);
            clearFile();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public List<StoreEntityCSV> findAll() {
        List<StoreEntityCSV> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                list.add(new StoreEntityCSV(Integer.parseInt(csvRecord.get(0)),
                        csvRecord.get(1), csvRecord.get(2)));
            }
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteAll() {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);
        createFileIfNotExist(sourceFile, outputFile);
        clearFile();
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }

    @Override
    public StoreEntityCSV findByName(String name) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                if (record.get(1).equals(name))
                    return new StoreEntityCSV(Integer.parseInt(record.get(0)),
                            record.get(1), record.get(2));
            }
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}