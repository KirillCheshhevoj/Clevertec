package main.ru.clevertec.Check;

import main.ru.clevertec.DiscountCard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<CheckItem> items;
    private Double total;
    private Double discount;
    private BigDecimal totalWithDiscount;
    private DiscountCard discountCard;

    public Check() {
        this.items = new ArrayList<>();
    }

    public List<CheckItem> getItems() {
        return items;
    }

    public void setItems(List<CheckItem> items) {
        this.items = items;
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

    public BigDecimal getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(BigDecimal totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }
}
