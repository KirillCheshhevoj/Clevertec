package main;

import main.ru.clevertec.Check.Check;
import main.ru.clevertec.Check.CheckInfo;
import main.ru.clevertec.CheckPrint;
import main.ru.clevertec.Exception.InternalServerError;
import main.ru.clevertec.Exception.NotEnoughMoney;
import main.ru.clevertec.Exception.ProductNotFoundException;
import main.ru.clevertec.File.FileDiscountCard;
import main.ru.clevertec.File.FileProductItem;
import main.ru.clevertec.Interface.DiscountCardInterface;
import main.ru.clevertec.Interface.ProductInterface;
import main.ru.clevertec.Product.ProductItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PRODUCT = "D:\\Java\\Clevertec\\Task_1\\src\\main\\resources\\products.csv";
    private static final String FILE_CARD = "D:\\Java\\Clevertec\\Task_1\\src\\main\\resources\\cards.csv";
    private static final String FILE_CHECK = "D:\\Java\\Clevertec\\Task_1\\src\\main\\resources\\check.csv";
    public static void main(String[] args) {
        try {
            List<ProductItem> items = new ArrayList<>();
            int discountCard = 0;
            BigDecimal balance = BigDecimal.ZERO;

            for (String arg : args) {
                if (arg.startsWith("discountCard")) {
                    try {
                        String card = arg.split("=")[1];
                        if (!card.matches("\\d{4}")) throw new InternalServerError("Error");
                        discountCard = Integer.parseInt(card);
                    } catch (InternalServerError | NumberFormatException e) {
                        throw new InternalServerError("Error format");
                    }
                } else if (arg.startsWith("balanceDebitCard")) {
                    try {
                        balance = new BigDecimal(arg.split("=")[1]);
                        balance = balance.setScale(2, RoundingMode.HALF_UP);
                    } catch (NumberFormatException exception) {
                        throw new InternalServerError("Error balance");
                    }
                } else {
                    try {
                        String[] parts = arg.split("-");
                        if (parts.length != 2) throw new InternalServerError("Error product format");
                        int productId = Integer.parseInt(parts[0]);
                        int quantity = Integer.parseInt(parts[1]);
                        if (quantity <= 0) throw new InternalServerError("Error product quantity");
                        items.add(new ProductItem(productId, quantity));
                    } catch (NumberFormatException exception) {
                        throw new InternalServerError("Error product");
                    }
                }
            }

            if (balance == null) {
                throw new InternalServerError("Balance not specified");
            }

            if (items.isEmpty()) {
                throw new InternalServerError("No products specified");
            }

            ProductInterface productInterface = new FileProductItem(FILE_PRODUCT);
            DiscountCardInterface discountCardInterface = new FileDiscountCard(FILE_CARD);
            CheckInfo checkInfo = new CheckInfo(productInterface, discountCardInterface);

            Check check = checkInfo.createCheck(items, discountCard, balance);
            CheckPrint printer = new CheckPrint();
            printer.printCheck(check);
            printer.save(check, FILE_CHECK);

        } catch (InternalServerError | ProductNotFoundException e) {
            System.out.println("BAD REQUEST\n" + e.getMessage());
            saveError("BAD REQUEST");
        } catch (NotEnoughMoney e) {
            System.out.println("NOT ENOUGH MONEY\n" + e.getMessage());
            saveError("NOT ENOUGH MONEY");
        } catch (Exception e) {
            System.out.println("INTERNAL SERVER ERROR" + e.getMessage());
            saveError("INTERNAL SERVER ERROR");
        }
    }

    private static void saveError(String errorMessage) {
        try {
            CheckPrint printer = new CheckPrint();
            printer.save(null, FILE_CHECK, errorMessage);
        } catch (Exception e) {
            System.out.println("Error saving error message to CSV: " + e.getMessage());
        }
    }
}
