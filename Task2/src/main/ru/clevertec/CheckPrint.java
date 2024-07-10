package main.ru.clevertec;

import main.ru.clevertec.Check.Check;
import main.ru.clevertec.Check.CheckItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckPrint {
    private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy;HH:mm:ss");

    public void printCheck(Check check) {
        System.out.println(formatCheck(check));
    }

    public void save(Check check, String filePath) {
        save(check, filePath, null);
    }

    public void save(Check check, String filePath, String error) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            if (error != null) {
                writer.println(error);
            } else {
                writer.println(formatCheck(check));
            }
        } catch (IOException exception) {
            System.out.println("File not writing: " + exception.getMessage());
        }
    }

    private String formatCheck(Check check) {
        StringBuilder sb = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();

        sb.append("Date;Time\n");
        sb.append(now.format(FORMAT)).append("\n\n");

        sb.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
        for (CheckItem item : check.getItems()) {
            sb.append(String.format("%d;%s;%.2f$;%.2f$;%.2f$\n",
                    item.getQuantity(),
                    item.getProduct().getDescription(),
                    item.getProduct().getPrice(),
                    item.getDiscount(),
                    item.getTotal()));
        }

        sb.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        sb.append(String.format("%.2f$;%.2f$;%.2f$",
                check.getTotal(),
                check.getDiscount(),
                check.getTotalWithDiscount()));

        return sb.toString();
    }
}
