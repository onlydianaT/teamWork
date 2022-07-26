package ru.koylubaevaem.task3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
//        1.Создание массива продуктов внутри программы (без пользовательского ввода);
        String[] products = {
                "meat, (1kg)",
                "milk, (1l)",
                "loaf of bread",

                "tea, (0.1kg)",
                "sugar, (1kg)",
                "potato, (1kg)",
                "buckwheat, (1kg)"
        };
//        2.Создание массива цен на продукты (без пользовательского ввода);
        int[] prices = {427, 73, 42, 90, 78, 63, 129};
        int[] quantity = new int[7];
        // 3.Вывод списка доступных для покупки продуктов на экран;
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ": " + products[i] + " " + prices[i] + " rub.");
        }

        int totalSum = 0;
        int itemOfProduct = 0;
        int amountOfProduct = 0;
        while (true) {
//            4.Возможность ввода пользователем одной строкой номера продукта и количества для добавления в корзину;
            System.out.println("Choose the item and amount or 'end' to quit: ");

            String inputStr = scan.nextLine();
            if (inputStr.equals("end")) {
                break;
            }
            String[] parts = inputStr.split(" "); //1..N 1 10 -> [1, 10]

            if (parts.length != 2) {
                System.out.println("Data were entered incorrectly. Type in an item, then gap, then amount.");
                continue;
            }
            try {
                itemOfProduct = Integer.parseInt(parts[0]) - 1; // 0
                amountOfProduct = Integer.parseInt(parts[1]); // 10
            } catch (NumberFormatException e) {
                System.out.println("Data were entered incorrectly.");
                continue;
            }
            if (itemOfProduct < 0 || amountOfProduct < 0) {
                System.out.println("Data were entered incorrectly. The position of product or its amount " +
                        "can't be negative");
                continue;
            }

            if (itemOfProduct > products.length) {
                System.out.println("There is no item with this number.");
                continue;
            }
//            5.Пользователь может добавлять несколько раз один и тот же товар в корзину, в этом случае он должен суммировать
            quantity[itemOfProduct] += amountOfProduct;
            totalSum += amountOfProduct * prices[itemOfProduct];

        }
//        6.Вывод всех покупок, их общую стоимость и количество, на экран после ввода всех покупок.
        for (int i = 0; i < quantity.length; i++) {
            if (quantity[i] != 0) {
                System.out.println(products[i] + " " + quantity[i] + " items " + prices[i] + " rub/item, " + (quantity[i] * prices[i]) + " rub " + " totally");
            }
        }
        System.out.println("Total: " + totalSum + " rub");
    }
}

