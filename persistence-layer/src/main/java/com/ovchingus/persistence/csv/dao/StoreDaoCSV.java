package com.ovchingus.persistence.csv.dao;

import com.ovchingus.persistence.csv.entities.StoreEntityCSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoCSV extends ConnectionCSV<StoreEntityCSV> {
    private static final Logger log = LogManager.getLogger(StoreDaoCSV.class);

    public StoreDaoCSV() {
        this.filePath += "stores.csv";
        this.tempPath += "tempStores.csv";
    }

    @Override
    public boolean persist(StoreEntityCSV entity) {
        if (initialize(filePath, tempPath)) {
            List<StoreEntityCSV> list = findAll();
            for (StoreEntityCSV item : list)
                if (item.getId().equals(entity.getId())
                        || item.getName().equals(entity.getName()))
                    return false;
            try {
                FileUtils.writeStringToFile(new File(filePath),
                        (entity.getId() + "," + entity.getName() + "," + entity.getAddress() + "\n"),
                        (Charset) null, true);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                return false;
            } finally {
                clearFile();
            }
        } else {
            log.error("Persist " + filePath + " rejected. Initialize error");
            return false;
        }
    }

    @Override
    public boolean update(StoreEntityCSV entity) {
        if (initialize(filePath, tempPath)) {

            StoreEntityCSV e = findById(entity.getId());
            delete(e);
            clearFile();
            return persist(entity);
        } else {
            log.error("Update " + filePath + " rejected. Initialize error");
            return false;
        }
    }

    @Override
    public StoreEntityCSV findById(Integer id) {
        if (initialize(filePath, tempPath)) {
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
        } else {
            log.error("FindByID " + filePath + " rejected. Initialize error");
            return null;
        }
    }

    @Override
    public boolean delete(StoreEntityCSV entity) {
        if (initialize(filePath, tempPath)) {
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
                return sourceFile.delete() && outputFile.renameTo(sourceFile);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                return false;
            } finally {
                clearFile();
            }
        } else {
            log.error("Delete " + filePath + " rejected. Initialize error");
            return false;
        }
    }

    @Override
    public List<StoreEntityCSV> findAll() {
        if (initialize(filePath, tempPath)) {
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
        } else {
            log.error("FindAll " + filePath + " rejected. Initialize error");
            return null;
        }
    }

    @Override
    public boolean deleteAll() {
        return deleteAll(filePath, tempPath);
    }

    @Override
    public StoreEntityCSV findByName(String name) {
        if (initialize(filePath, tempPath)) {
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
        } else {
            log.error("Delete all " + filePath + " rejected. Initialize error");
            return null;
        }
    }

    @Override
    public boolean contains(String name) {
        StoreEntityCSV product = findByName(name);
        return product != null;
    }
}