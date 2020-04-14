import java.util.Objects;

public class Food {

    /**
     * Конструктор создания продукта
     *
     * @param name - имя добавляемого продукта
     * @param type - тип(клас/принадлежность) продукта
     * @param cost - стоимость продукта
     * @param description - описание продукта
     */
    public Food(String name, String type, int cost, String description){
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.type = type;
    }

    private String name;
    private int cost;
    private String description;
    private String type;

    /** Возвращает имя продукта */
    public String getName(){
        return name;
    }

    /** Возвращает стоимость продукта */
    public int getCost(){
        return cost;
    }

    /** Возвращает описание продукта */
    public String getDescription(){
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return cost == food.cost &&
                Objects.equals(name, food.name) &&
                Objects.equals(description, food.description) &&
                Objects.equals(type, food.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, description, type);
    }
}
