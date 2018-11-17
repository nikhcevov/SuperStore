package com.ovchingus.persistence.CSV.dao;

import com.ovchingus.persistence.CSV.entities.ProductEntityCSV;
import com.ovchingus.persistence.CSV.entities.ProductInfo;
import com.ovchingus.persistence.settings.DaoSettings;
import com.sun.istack.internal.Nullable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoCSV extends ConnectionCSV<ProductEntityCSV> {

    private String filePath = DaoSettings.getCsvFilePath() + "products.csv";
    private String tempPath = DaoSettings.getCsvFilePath() + "tempProducts.csv";
    private Logger log = LogManager.getLogger(ProductEntityCSV.class);
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
    public void persist(ProductEntityCSV entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getId()).append(",");
        sb.append(entity.getName()).append(",");
        if (entity.getProducts() != null)
            for (ProductInfo pi : entity.getProducts()) {
                sb.append(pi.getStoreId()).append(",");
                sb.append(pi.getQty()).append(",");
                sb.append(pi.getPrice()).append(",");
            }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('\n');

        try {
            FileUtils.writeStringToFile(new File(filePath), sb.toString(),
                    (Charset) null, true);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        clearFile();
    }

    @Override
    public void update(ProductEntityCSV entity) {
        List<ProductEntityCSV> list = findAll();
        for (ProductEntityCSV item : list) {
            if (item.getId().equals(entity.getId()))
                delete(item);
        }
        persist(entity);
    }

    @Override
    public ProductEntityCSV findById(Integer id) {
        ProductEntityCSV pe = new ProductEntityCSV();
        List<ProductEntityCSV> list = findAll();
        for (ProductEntityCSV item : list) {
            if (item.getId().equals(id)) {
                pe = item;
            }
        }
        clearFile();
        return pe;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void delete(ProductEntityCSV entity) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            StringBuilder sb = new StringBuilder();
            sb.append(entity.getId()).append(",");
            sb.append(entity.getName()).append(",");
            for (ProductInfo pi : entity.getProducts()) {
                sb.append(pi.getStoreId()).append(",");
                sb.append(pi.getQty()).append(",");
                sb.append(pi.getPrice()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            while ((line = reader.readLine()) != null) {
                if (!line.equals(sb.toString())) {
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
    public List<ProductEntityCSV> findAll() {
        List<ProductEntityCSV> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] items = line.split(",");
                List<ProductInfo> pi = new ArrayList<>();
                for (int i = 2; i < items.length; i++) {
                    pi.add(new ProductInfo(Integer.parseInt(items[i++]),
                            Integer.parseInt(items[i++]),
                            Double.parseDouble(items[i])));
                }
                ProductEntityCSV pcs = new ProductEntityCSV();
                pcs.setId(Integer.parseInt(items[0]));
                pcs.setName(items[1]);
                pcs.setProducts(pi);
                list.add(pcs);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        clearFile();
        return list;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteAll() {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);
        createFileIfNotExist(sourceFile, outputFile);
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
        clearFile();
    }

    @Override
    @Nullable
    public ProductEntityCSV findByName(String name) {
        List<ProductEntityCSV> list = findAll();
        for (ProductEntityCSV item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        clearFile();
        return null;
    }
}
