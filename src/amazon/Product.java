package amazon;
public class Product {
    private String productId;
    private String productName;
    private String brand;
    private double price;
    private double rating;
    private int reviewCount;

    
    public Product(String productId, String productName, String brand, double price, double rating, int review) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.reviewCount = review; 
    }

    
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public int getReviewCount() { return reviewCount; }

    // Discount logic
    public double getDiscountedPrice(double discountPercentage) {
        double discountAmount = price * discountPercentage / 100;
        return price - discountAmount;
    }

    // toString
    @Override
    public String toString() {
        return "Product ID: " + productId +
               " | Product Name: " + productName +
               " | Brand: " + brand +
               " | Price: " + price +
               " | Rating: " + rating +
               " | Reviews: " + reviewCount;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product p = (Product) obj;
        return this.productId.equals(p.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}