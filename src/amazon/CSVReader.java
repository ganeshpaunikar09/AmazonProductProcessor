package amazon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Product> readProducts(String filePath) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            // Skip header
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // Simple validation
                if (parts.length != 6) {
                    System.out.println("Invalid CSV format: " + line);
                    continue;
                }

                String productId = parts[0];
                String productName = parts[1];
                String brand = parts[2];
                double price = Double.parseDouble(parts[3]);
                double rating = Double.parseDouble(parts[4]);
                int reviewCount = Integer.parseInt(parts[5]);

                Product p = new Product(productId, productName, brand, price, rating, reviewCount);
                products.add(p);
            }

        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format error in CSV: " + e.getMessage());
        }

        return products;
    }

    // Test main for my test 
    public static void main(String[] args) {
        String filePath = "C:\\workspaces\\seleniumwebdriver\\AmazonProductProcessor\\src\\products.csv";
        List<Product> productList = readProducts(filePath);

        productList.forEach(System.out::println);
    }
}