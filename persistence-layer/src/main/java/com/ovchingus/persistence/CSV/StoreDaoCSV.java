package com.ovchingus.persistence.CSV;

import com.ovchingus.persistence.CSV.model.StoreEntityCSV;
import com.ovchingus.persistence.settings.DaoSettings;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoCSV extends ConnectionCSV<StoreEntityCSV, Integer> {

    @Override
    public void persist(StoreEntityCSV entity) {
        String filePath = DaoSettings.getCsvFilePath() + "stores.csv";

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             FileWriter fileWriter = new FileWriter(filePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);) {


            fileWriter.append(entity.getId() + "," + entity.getName() + "," + entity.getAddress());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(StoreEntityCSV entity) {
        List<StoreEntityCSV> list = findAll();

        StoreEntityCSV e = findById(entity.getId());
        delete(e);
        persist(entity);
    }

    @Override
    public StoreEntityCSV findById(Integer id) {
        String fileName = DaoSettings.getCsvFilePath() + "stores.csv";
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

            for (CSVRecord record : csvParser) {
                Integer curId = Integer.parseInt(record.get(0));
                if (curId.equals(id))
                    return new StoreEntityCSV(Integer.parseInt(record.get(0)),
                            record.get(1), record.get(2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(StoreEntityCSV entity) {
        String sourcePath = DaoSettings.getCsvFilePath() + "stores.csv";
        String outPath = DaoSettings.getCsvFilePath() + "tempStores.csv";

        File sourceFile = new File(sourcePath);
        File outputFile = new File(outPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StoreEntityCSV> findAll() {
        String filePath = DaoSettings.getCsvFilePath() + "stores.csv";
        List<StoreEntityCSV> list = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {

            for (CSVRecord csvRecord : csvParser) {
                list.add(new StoreEntityCSV(Integer.parseInt(csvRecord.get(0)),
                        csvRecord.get(1), csvRecord.get(2)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteAll() {
        String sourcePath = DaoSettings.getCsvFilePath() + "stores.csv";
        String outPath = DaoSettings.getCsvFilePath() + "tempStores.csv";

        File sourceFile = new File(sourcePath);
        File outputFile = new File(outPath);

        try {
            if (!sourceFile.exists())
                sourceFile.createNewFile();
            if (!outputFile.exists())
                outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }

    @Override
    public StoreEntityCSV findByName(String name) {
        String fileName = DaoSettings.getCsvFilePath() + "stores.csv";
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

            for (CSVRecord record : csvParser) {
                if (record.get(1).equals(name))
                    return new StoreEntityCSV(Integer.parseInt(record.get(0)),
                            record.get(1), record.get(2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


