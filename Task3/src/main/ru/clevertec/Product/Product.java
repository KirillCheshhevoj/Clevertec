package main.ru.clevertec.Product;

public class Product {
    private int id;
    private String description;
    private double price;
    private boolean wholesale;

    public Product(int id, String description, double price, boolean wholesale) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.wholesale = wholesale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isWholesale() {
        return wholesale;
    }

    public void setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
    }
}
