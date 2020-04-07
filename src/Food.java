public class Food {

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

    public String getName(){
        return name;
    }
    public int getCost(){
        return cost;
    }
    public String getDescription(){
        return description;
    }
}
