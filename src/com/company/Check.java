package com.company;

import java.util.ArrayList;

public class Check {

    private int price;
    private static ArrayList<Food> shopList = new ArrayList<>();

    public void addFood(Food food){
        shopList.add(food);
    }

    public void deleteFood(int del){
        shopList.remove(del);
    }

    public int price(){
        int ch = 0;
        for (int i = 0; i < shopList.size(); i++){
            ch += shopList.get(i).getCost();
        }
        return ch;
    }

    public void drawShopList(){
        for (int i = 0; i < shopList.size(); i++){
            System.out.print(i+1 + ". ");
            System.out.println(shopList.get(i).getName());
        }
    }

    public int getShopListSize(){
        return shopList.size();
    }
}
