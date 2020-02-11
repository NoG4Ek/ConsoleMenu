package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Menu m = new Menu();
        Check c = new Check();
        Scanner sc = new Scanner(System.in);
        String str;
        int state = 1;
        int lastType = 0;
        int lastFood = 0;

        m.assemble();

        while (state != 0){
            switch (state){
                case 1:
                    System.out.println("~~ МЕНЮ ~~");
                    m.drawStartMenu();
                    str = sc.nextLine();
                    switch (str){
                        case "0":
                            state -= 1;
                            break;
                        case "Order":
                            state = 4;
                            break;
                        default:
                            if (str.matches("\\d") && 0 < Integer.parseInt(str) &&
                                    Integer.parseInt(str) <= m.getMenuList().keySet().size()) {
                                state = 2;
                                lastType = Integer.parseInt(str) - 1;
                            } else {
                                System.out.println("Неверно введенный тип блюда, попробуйте еще раз");
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("~~ " + m.getMenuList().keySet().toArray()[lastType] + " ~~");
                    m.drawProducts((String) m.getMenuList().keySet().toArray()[lastType]);
                    str = sc.nextLine();
                    switch (str){
                        case "0":
                            state -= 1;
                            break;
                        case "Order":
                            state = 4;
                            break;
                        default:
                            if (str.matches("\\d") && 0 < Integer.parseInt(str) &&
                                    Integer.parseInt(str) <= m.getTypeSize((String) m.getMenuList().keySet().toArray()[lastType])) {
                                state = 3;
                                lastFood = Integer.parseInt(str) - 1;
                            } else {
                                System.out.println("Неверно введенный номер блюда, попробуйте еще раз");
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("~~ " + m.getFood(lastType, lastFood).getName() + " ~~" + "\nОписание:");
                    m.drawDescription(lastType, lastFood);
                    System.out.println("\n\"Buy\" - добавить этот товар в ваш заказ");
                    str = sc.nextLine();
                    switch (str){
                        case "0":
                            state -= 1;
                            break;
                        case "Buy":
                            c.addFood(m.getFood(lastType, lastFood));
                            System.out.println("Товар успешно добавлен!\n");
                            state = 2;
                            break;
                        case "Order":
                            state = 4;
                            break;
                        default:
                            System.out.println("Ошибка ввода, попробуйте еще раз");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("~~ Ваш заказ ~~");
                    c.drawShopList();
                    System.out.println("~~ Итого ~~");
                    System.out.println(c.price() + " руб.");
                    System.out.println("\nВсе верно? (\"Yes\" / \"0\"-назад / \"Номер блюда\"-удалить блюдо)");
                    str = sc.nextLine();
                    switch (str) {
                        case "0":
                            state = 1;
                            break;
                        case "Yes":
                            System.out.println("~~ Спасибо за покупку! ~~");
                            state = 0;
                            break;
                        default:
                            if (str.matches("\\d") && 0 < Integer.parseInt(str) &&
                                    Integer.parseInt(str) <= c.getShopListSize()) {
                                c.deleteFood(Integer.parseInt(str) - 1);
                            } else {
                                System.out.println("Ошибка ввода, попробуйте еще раз");
                            }
                            break;
                    }
                    break;
            }
        }
    }
}

