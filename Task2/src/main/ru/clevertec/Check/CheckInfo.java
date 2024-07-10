package main.ru.clevertec.Check;

import main.ru.clevertec.DiscountCard;
import main.ru.clevertec.Exception.NotEnoughMoney;
import main.ru.clevertec.Exception.ProductNotFoundException;
import main.ru.clevertec.Interface.DiscountCardInterface;
import main.ru.clevertec.Interface.ProductInterface;
import main.ru.clevertec.Product.Product;
import main.ru.clevertec.Product.ProductItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckInfo {
    private ProductInterface productInterface;
    private DiscountCardInterface discountCardInterface;

    public CheckInfo(ProductInterface productInterface, DiscountCardInterface discountCardInterface) {
        this.productInterface = productInterface;
        this.discountCardInterface = discountCardInterface;
    }

    public Check createCheck(List<ProductItem> items, Integer discountCard, BigDecimal balance) {
        CheckBuilder builder = new CheckBuilder();
        Double total = 0.0;
        Double discount = 0.0;

        Map<Integer, Integer> productInteger = new HashMap<>();
        for (ProductItem item : items) {
            productInteger.merge(item.getProductId(), item.getQuantity(), Integer::sum);
        }

        for (Map.Entry<Integer, Integer> entry : productInteger.entrySet()) {
            int id = entry.getKey();
            int quantity = entry.getValue();

            Product product = productInterface.getId(id);
            if (product == null) {
                throw new ProductNotFoundException("Product not found " + id);
            }

            Double totalItem = product.getPrice() * quantity;
            Double discountItem = Discount(product, quantity, discountCard);

            builder.addItem(product, quantity, totalItem, discountItem);
            total = total + totalItem;
            discount = discount + discountItem;
        }

        BigDecimal totalWithDiscount = BigDecimal.valueOf(total - discount);

        if (balance.compareTo(totalWithDiscount) < 0) {
            throw new NotEnoughMoney("Not have money");
        }

        return builder
                .setTotal(total)
                .setDiscount(discount)
                .setTotalWithDiscount(totalWithDiscount)
                .setDiscountCard(discountCard != null ? discountCardInterface.getNumber(discountCard) : null)
                .build();
    }

    private Double Discount(Product product, int quantity, Integer discountCard) {
        Double discount = 0.0;
        if (product.isWholesale() && quantity > 5) {
            discount = product.getPrice() * quantity * 0.1;
        } else {
            if (discountCard != null) {
                DiscountCard card = discountCardInterface.getNumber(discountCard);
                discount = product.getPrice() * quantity * card.getDiscount() / 100;
            }
        }
        return discount;
    }
}
