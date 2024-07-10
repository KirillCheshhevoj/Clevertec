package main.ru.clevertec.File;

import main.ru.clevertec.Interface.ProductInterface;
import main.ru.clevertec.Product.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileProductItem implements ProductInterface {
    private Map<Integer, Product> products = new HashMap<>();

    public FileProductItem(String filePath) {
        loadProductsFromFile(filePath);
    }

    @Override
    public Product getId(int id) {
        return products.get(id);
    }

    private void loadProductsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                Double price = Double.parseDouble(parts[2]);
                boolean wholesale = Boolean.parseBoolean(parts[3]);
                products.put(id, new Product(id, description, price, wholesale));
            }
        } catch (IOException exception) {
            throw new RuntimeException("File not found : " + filePath, exception);
        }
    }
}
