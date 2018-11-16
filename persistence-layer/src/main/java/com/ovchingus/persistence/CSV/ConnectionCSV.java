package com.ovchingus.persistence.CSV;

import com.ovchingus.persistence.GenericDao;

import java.io.*;

abstract class ConnectionCSV<T, PK> implements GenericDao<T, Integer> {

    void clear(String filePath, String tempPath) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("\n")) {
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
}
