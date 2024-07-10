package main.ru.clevertec;

import main.ru.clevertec.Product.Product;

public class CheckItem {
    private Product product;
    private int quantity;
    private Double total;
    private Double discount;

    public CheckItem(Product product, int quantity, Double total, Double discount) {
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
