package com.ovchingus.persistence.CSV;

import com.ovchingus.persistence.CSV.model.ProductEntityCSV;
import com.ovchingus.persistence.CSV.model.ProductInfo;
import com.ovchingus.persistence.settings.DaoSettings;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoCSV extends ConnectionCSV<ProductEntityCSV, Integer> {

    private String filePath = DaoSettings.getCsvFilePath() + "products.csv";
    private String tempPath = DaoSettings.getCsvFilePath() + "tempProducts.csv";

    public void clearFile() {
        clear(filePath, tempPath);
    }

    @Override
    public void persist(ProductEntityCSV entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getId()).append(",");
        sb.append(entity.getName()).append(",");
        for (ProductInfo pi : entity.getProducts()) {
            sb.append(pi.getId()).append(",");
            sb.append(pi.getQty()).append(",");
            sb.append(pi.getPrice()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('\n');

        try {
            FileUtils.writeStringToFile(new File(filePath), sb.toString(), true);
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductEntityCSV entity) {

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
    public void delete(ProductEntityCSV entity) {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));) {
            String line;

            StringBuilder sb = new StringBuilder();
            sb.append(entity.getId()).append(",");
            sb.append(entity.getName()).append(",");
            for (ProductInfo pi : entity.getProducts()) {
                sb.append(pi.getId()).append(",");
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
        }
    }

    @Override
    public List<ProductEntityCSV> findAll() {
        List<ProductEntityCSV> list = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                BufferedReader br = new BufferedReader(reader);
        ) {

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
                clearFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteAll() {
        File sourceFile = new File(filePath);
        File outputFile = new File(tempPath);

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
        clearFile();
    }

    @Override
    public ProductEntityCSV findByName(String name) {
        ProductEntityCSV pe = new ProductEntityCSV();
        List<ProductEntityCSV> list = findAll();

        for (ProductEntityCSV item : list) {
            if (item.getName().equals(name)) {
                pe = item;
            }
        }
        clearFile();
        return pe;
    }
}
