package com.company;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private HashMap<String, ArrayList<Food>> menuList = new HashMap<>();
    private ArrayList<Food> list = new ArrayList<>();

    public void assemble() throws Exception {
        FileReader fr = new FileReader("Menu.txt");
        Scanner scMenu = new Scanner(fr);
        String type = "";
        String name = "";
        String description = "";
        int cost = 0;

        while (scMenu.hasNextLine()) {
            String line = scMenu.nextLine();

            if (line.isEmpty()){
                continue;
            }
            if (line.charAt(0) == '/' && line.charAt(1) == '/'){
                continue;
            } else
            if (line.charAt(0) == '@'){
                if (list.isEmpty() && !type.isEmpty()){
                    menuList.put(type, (ArrayList<Food>) list.clone());
                }
                if (!list.isEmpty()){
                    menuList.put(type, (ArrayList<Food>) list.clone());
                    list.clear();
                }
                type = line.substring(1).trim();
            } else
            if (line.matches("(Название:)[\\s\\S]+")){
                name = line.substring(9).trim();
            } else
            if (line.matches("(Цена:)[\\s\\S]+")){
                cost = Integer.parseInt(line.substring(5).trim());
            } else
            if (line.matches("(Описание:)[\\s\\S]*")){
                description = line.substring(9).trim();
                Food food = new Food(name, type, cost, description);
                list.add(food);
            }
        }
        fr.close();
        if (list.isEmpty() && !type.isEmpty()){
            menuList.put(type, (ArrayList<Food>) list.clone());
        }
        if (!list.isEmpty()) {
            menuList.put(type, (ArrayList<Food>) list.clone());
            list.clear();
        }
    }

    public HashMap getMenuList(){
        return menuList;
    }

    public void drawStartMenu(){
        for (int i = 0; i < menuList.keySet().size(); i++){
            System.out.print(i+1 + ". ");
            System.out.println(menuList.keySet().toArray()[i]);
        }
    }

    public void drawProducts(String type) {
        for (int i = 0; i < menuList.get(type).size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.print(menuList.get(type).get(i).getName());
            System.out.println("    " + menuList.get(type).get(i).getCost() + " руб.");
        }
    }

    public void drawDescription(int type, int food){
        System.out.println("Цена: " + menuList.get(menuList.keySet().toArray()[type]).get(food).getCost() + " руб.");
        System.out.println(menuList.get(menuList.keySet().toArray()[type]).get(food).getDescription());
    }

    public Food getFood(int type, int food) {
        return menuList.get(menuList.keySet().toArray()[type]).get(food);
    }

    public int getTypeSize(String type){
        return menuList.get(type).size();
    }

}
