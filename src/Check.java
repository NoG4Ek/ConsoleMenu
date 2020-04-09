import java.util.ArrayList;
import java.util.List;

public class Check {

    private int price;
    private List<Food> shopList = new ArrayList<>();

    public void addFood(Food food){
        shopList.add(food);
    }

    public void deleteFood(int del){
        shopList.remove(del);
    }

    public int price(){
        int ch = 0;
        for (Food product: shopList){
            ch += product.getCost();
        }
        return ch;
    }

    @Override
    public String toString(){
        StringBuilder check = new StringBuilder();
        int i = 1;
        for (Food product: shopList){
            check.append(i).append(". ");
            check.append(product.getName());
            if (i != shopList.size())
                check.append(System.lineSeparator());
            i++;
        }
        return check.toString();
    }

    public int getShopListSize(){
        return shopList.size();
    }
}
