package main.ru.clevertec.Check;

import main.ru.clevertec.Check.Check;
import main.ru.clevertec.DiscountCard;
import main.ru.clevertec.Product.Product;

import java.math.BigDecimal;

public class CheckBuilder {
    private Check check;

    public CheckBuilder() {
        check = new Check();
    }

    public CheckBuilder addItem(Product product, int quantity, Double total, Double discount) {
        check.getItems().add(new main.ru.clevertec.CheckItem(product, quantity, total, discount));
        return this;
    }

    public CheckBuilder setTotal(Double total) {
        check.setTotal(total);
        return this;
    }

    public CheckBuilder setDiscount(Double discount) {
        check.setDiscount(discount);
        return this;
    }

    public CheckBuilder setTotalWithDiscount(BigDecimal totalWithDiscount) {
        check.setTotalWithDiscount(totalWithDiscount);
        return this;
    }

    public CheckBuilder setDiscountCard(DiscountCard discountCard) {
        check.setDiscountCard(discountCard);
        return this;
    }

    public Check build() {
        return check;
    }
}

