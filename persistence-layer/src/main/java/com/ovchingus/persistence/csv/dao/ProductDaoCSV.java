package com.ovchingus.persistence.csv.dao;

import com.ovchingus.persistence.csv.entities.ProductEntityCSV;
import com.ovchingus.persistence.csv.entities.ProductInfo;
import com.ovchingus.persistence.settings.DaoSettings;
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
    public boolean persist(ProductEntityCSV entity) {
        List<ProductEntityCSV> list = findAll();
        for (ProductEntityCSV item : list)
            if (item.getId().equals(entity.getId())
                    || item.getName().equals(entity.getName()))
                return false;
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        } finally {
            clearFile();
        }
    }

    @Override
    public boolean update(ProductEntityCSV entity) {
        List<ProductEntityCSV> list = findAll();
        for (ProductEntityCSV item : list) {
            if (item.getId().equals(entity.getId()))
                delete(item);
        }
        return persist(entity);
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
    public boolean delete(ProductEntityCSV entity) {
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
            return sourceFile.delete() && outputFile.renameTo(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        } finally {
            clearFile();
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
    public boolean deleteAll() {
        return deleteAll(filePath, tempPath);
    }

    @Override
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
