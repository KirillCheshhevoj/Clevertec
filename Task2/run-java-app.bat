@echo off
REM Установим переменные для параметров
SET id_quantity=101-2
SET discountCard=1234
SET balanceDebitCard=5678
SET pathToFile=src\main\resources\products.csv
SET saveToFile=src\main\resources\check.csv

REM Запустим Java приложение
java -cp src ./src/main/ru/Main.java %id_quantity% discountCard=%discountCard% balanceDebitCard=%balanceDebitCard% pathToFile=%pathToFile% saveToFile=%saveToFile%

REM Пауза перед выходом
pause
