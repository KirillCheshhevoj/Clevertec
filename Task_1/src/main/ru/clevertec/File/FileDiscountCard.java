package main.ru.clevertec.File;

import main.ru.clevertec.DiscountCard;
import main.ru.clevertec.Interface.DiscountCardInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileDiscountCard implements DiscountCardInterface {
    private Map<Integer, DiscountCard> discountCards = new HashMap<>();

    public FileDiscountCard(String filePath) {
        loadDiscountCardsFromFile(filePath);
    }

    @Override
    public DiscountCard getNumber(int number) {
        int DISCOUNT_NOT_CARD = 2;
        DiscountCard card = discountCards.get(number);
        if (card == null) {
            return new DiscountCard(number, DISCOUNT_NOT_CARD);
        }
        return card;
    }

    private void loadDiscountCardsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int number = Integer.parseInt(parts[0]);
                int discount = Integer.parseInt(parts[1]);
                discountCards.put(number, new DiscountCard(number, discount));
            }
        } catch (IOException exception) {
            throw new RuntimeException("File not found: " + filePath, exception);
        }
    }
}
