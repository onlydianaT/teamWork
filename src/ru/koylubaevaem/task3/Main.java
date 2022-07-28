package ru.koylubaevaem.task3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1. Создание массива продуктов внутри программы (без пользовательского ввода);
        String meat = "meat, (1kg)";
        String potato = "potato, (1kg)";
        String buckwheat = "buckwheat, (1kg)";
        String[] products = {
                meat,
                "milk, (1l)",
                "loaf of bread",
                "tea, (0.1kg)",
                "sugar, (1kg)",
                potato,
                buckwheat
        };

        // 2. Создание массива цен на продукты (без пользовательского ввода);
        int[] prices = {427, 73, 42, 90, 78, 63, 129};
        int[] quantity = new int[7];

        // В программе теперь появится новый массив из названий товаров, которые продаются
        // по принципу “3 по цене 2х”. Таким образом, за каждые три штуки товара из массива
        // в корзине цена должна начисляться как за два.
        String[] promoProducts = {meat, potato, buckwheat};

        // 3. Вывод списка доступных для покупки продуктов на экран;
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ": " + products[i] + " " + prices[i] + " rub.");
        }
        System.out.println("Promotional items, 3 for the price of 2: ");
        for (int i = 0; i < promoProducts.length; i++) {
            System.out.println((i + 1) + ": " + promoProducts[i]);
        }
        int itemOfProduct = 0;
        int amountOfProduct = 0;
        while (true) {
            // 4. Возможность ввода пользователем одной строкой номера продукта и количества для добавления в корзину;
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
            if (itemOfProduct < 0) {
                System.out.println("Data were entered incorrectly. The position of product or its amount " +
                        "can't be negative or zero");
                continue;
            }
            if (itemOfProduct > products.length) {
                System.out.println("There is no item with this number.");
                continue;
            }
            boolean isQuantityNegative = amountOfProduct < 0 && (quantity[itemOfProduct] + amountOfProduct) < 0;
            int totalSum = 0;
            if (amountOfProduct == 0 || isQuantityNegative) {
                quantity[itemOfProduct] = 0;
            } else {
//            5.Пользователь может добавлять несколько раз один и тот же товар в корзину, в этом случае он должен суммировать
                quantity[itemOfProduct] += amountOfProduct;
            }
        }
        // 6. Вывод всех покупок, их общую стоимость и количество, на экран после ввода всех покупок.
        int totalSum = 0;
        String nameOfGood;
        for (int i = 0; i < quantity.length; i++) {
            if (quantity[i] > 0) {
                // Проверяем акционный товар или нет
                nameOfGood = products[i];
                boolean promo = isPromo(promoProducts, nameOfGood);
                int sum;
                if (promo) {
                    sum = ((quantity[i] / 3) * 2 + (quantity[i] % 3)) * prices[i];
                    System.out.println("Discount price: " + products[i] + " " + quantity[i] + " items " + prices[i] + " rub/item, " + sum + " rub " + " totally, " + "without discount: " + quantity[i] * prices[i]);
                } else {
                    sum = quantity[i] * prices[i];
                    System.out.println("Price without discount : " + products[i] + " " + quantity[i] + " items " + prices[i] + " rub/item, " + sum + " rub " + " totally");
                }
                totalSum += sum;
            }
        }
        System.out.println("Total: " + totalSum + " rub");
    }

    private static boolean isPromo(String[] promoProducts, String nameOfGood) {
        for (String item : promoProducts) {
            if (nameOfGood.equals(item)) {
                return true;
            }
        }
        return false;
    }
}

