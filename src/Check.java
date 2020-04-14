import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Check {

    //Может использоваться для кэширования
    private int price;

    /** Лист купленных продуктов */
    private List<Food> shopList = new ArrayList<>();

    /**
     * Функция добавления продукта в список
     * @see #shopList
     *
     * @param food - имя добавляемого продукта
     * @return возвращает результат выполнения в формате true/false
     */
    public boolean addFood(Food food){
        return shopList.add(food);
    }

    /**
     * Функция удаления продукта из списка
     * @see #shopList
     *
     * @param del - номер удаляемого продукта из списка
     */
    public void deleteFood(int del){
        shopList.remove(del);
    }

    /**
     * Функция нахождения итоговой цены
     * @return возвращает цену
     */
    public int price() {
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

    /** Функция возвращает количество купленных продуктов */
    public int getShopListSize(){
        return shopList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return price == check.price &&
                Objects.equals(shopList, check.shopList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, shopList);
    }
}
