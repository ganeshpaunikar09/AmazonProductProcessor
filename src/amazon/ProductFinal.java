package amazon;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ProductFinal {
    public static void main(String[] args) {
        String filePath = "src/products.csv";
        List<Product> products = CSVReader.readProducts(filePath);
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        System.out.println("\n--- All Products ---");
        products.forEach(System.out::println);

        //Top Rated Product
        Product topRated = products.stream()
                .max((p1, p2) -> Double.compare(p1.getRating(), p2.getRating()))
                .orElse(null);
        System.out.println("\nTop Rated Product: " + topRated);

        //Most Expensive Product Per Brand
        Map<String, Product> expensivePerBrand = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getBrand,
                        Collectors.collectingAndThen(
                                Collectors.maxBy((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice())),
                                opt -> opt.orElse(null)
                        )
                ));
        System.out.println("\nMost Expensive Product per Brand:");
        expensivePerBrand.forEach((brand, product) ->
                System.out.println(brand + " → " + product.getProductName()));

        //Filter Products
        List<Product> filtered = products.stream()
                .filter(p -> p.getRating() >= 4.3 && p.getPrice() < 80000)
                .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
                .collect(Collectors.toList());
        System.out.println("\nFiltered Products (Rating >= 4.3 & Price < 80000):");
        filtered.forEach(System.out::println);

        //Validate Sorting Logic
        List<Double> prices = products.stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());
        boolean isSorted = true;
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i - 1) > prices.get(i)) {
                isSorted = false;
                break;
            }
        }
        System.out.println("\nPrices sorted low → high? " + isSorted);

        //Average Price per Brand
        Map<String, Double> avgPricePerBrand = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getBrand,
                        Collectors.averagingDouble(Product::getPrice)
                ));
        System.out.println("\nAverage Price per Brand:");
        avgPricePerBrand.forEach((brand, avg) ->
                System.out.println(brand + " → " + avg));

        //Most Reviewed Product
        Product mostReviewed = products.stream()
                .max((p1, p2) -> Integer.compare(p1.getReviewCount(), p2.getReviewCount()))
                .orElse(null);
        System.out.println("\nMost Reviewed Product: " + mostReviewed);

        //Discount Logic Example
        Product exampleProduct = products.get(0);
        int discount =10; // 10% discount
        double discountedPrice = exampleProduct.getDiscountedPrice(discount); // 10% discount
        System.out.println("\n" + exampleProduct.getProductName() + " price after "+discount+"% discount: " + discountedPrice);
    }
}
