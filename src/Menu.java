import org.kohsuke.args4j.Option;

import java.io.FileReader;
import java.util.*;

public class Menu {
    private Map<String, List<Food>> menuMap = new HashMap<>();

    @Option(name="-p",usage="Set the path to the menu\n", required = false)
    private String path;

    public void assemble() {

        try (FileReader fr = new FileReader("./Menu.txt")) {
            List<Food> list = new ArrayList<>();
            Scanner scMenu = new Scanner(fr);
            String type = "";
            String name = "";
            String description = "";
            int cost = 0;

            while (scMenu.hasNextLine()) {
                String line = scMenu.nextLine();

                if (line.isEmpty()) {
                    continue;
                }
                if (line.charAt(0) == '/' && line.charAt(1) == '/') {
                    continue;
                } else if (line.charAt(0) == '@') {
                    if (list.isEmpty() && !type.isEmpty()) {
                        List clone = new ArrayList(list);
                        menuMap.put(type, (ArrayList<Food>) clone);
                    }
                    if (!list.isEmpty()) {
                        List clone = new ArrayList(list);
                        menuMap.put(type, (ArrayList<Food>) clone);
                        list.clear();
                    }
                    type = line.substring(1).trim();
                } else if (line.matches("(Название:)[\\s\\S]+")) {
                    name = line.substring(9).trim();
                } else if (line.matches("(Цена:)[\\s\\S]+")) {
                    cost = Integer.parseInt(line.substring(5).trim());
                } else if (line.matches("(Описание:)[\\s\\S]*")) {
                    description = line.substring(9).trim();
                    Food food = new Food(name, type, cost, description);
                    list.add(food);
                }
            }
            if (list.isEmpty() && !type.isEmpty()) {
                List clone = new ArrayList(list);
                menuMap.put(type, (ArrayList<Food>) clone);
            }
            if (!list.isEmpty()) {
                List clone = new ArrayList(list);
                menuMap.put(type, (ArrayList<Food>) clone);
                list.clear();
            }
        } catch (Throwable e) {
            Throwable[] suppExe = e.getSuppressed();

            for (Throwable throwable : suppExe) {
                System.out.println("Suppressed Exceptions:");
                System.out.println(throwable);
            }
        }
    }

    public int getMapSize() { return menuMap.keySet().size(); }

    public String getTypeName(int typeNumber) { return (String) menuMap.keySet().toArray()[typeNumber]; }

    public void drawStartMenu(){
        for (int i = 0; i < menuMap.keySet().size(); i++){
            System.out.print(i+1 + ". ");
            System.out.println(menuMap.keySet().toArray()[i]);
        }
    }

    public void drawProducts(String type) {
        for (int i = 0; i < menuMap.get(type).size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.print(menuMap.get(type).get(i).getName());
            System.out.println("    " + menuMap.get(type).get(i).getCost() + " rub.");
        }
    }

    public void drawDescription(int type, int food){
        Object selectedType = menuMap.keySet().toArray()[type];
        System.out.println("Price: " + menuMap.get(selectedType).get(food).getCost() + " rub.");
        System.out.println(menuMap.get(selectedType).get(food).getDescription());
    }

    public Food getFood(int type, int food) {
        Object selectedType = menuMap.keySet().toArray()[type];
        return menuMap.get(selectedType).get(food);
    }

    public int getTypeSize(String type){
        return menuMap.get(type).size();
    }
}
