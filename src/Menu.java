import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.*;

public class Menu {
    private Map<String, List<Food>> menuMap = new HashMap<>();

    @Option(name="-p",usage="Set the path to the menu", required = false)
    private String path;

    public void assemble() {
        try (InputStream in = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            List<Food> list = new ArrayList<>();
            Scanner scMenu = new Scanner(reader);
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
                    checkAndPut(list, type);
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
            checkAndPut(list, type);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e){
            System.err.println("Файл отсутствует");
        }
    }

    private void checkAndPut(List<Food> list, String type) {
        if (list.isEmpty() && !type.isEmpty()) {
            List clone = new ArrayList(list);
            menuMap.put(type, (ArrayList<Food>) clone);
        }
        if (!list.isEmpty()) {
            List clone = new ArrayList(list);
            menuMap.put(type, (ArrayList<Food>) clone);
            list.clear();
        }
    }

    public int getMapSize() { return menuMap.keySet().size(); }

    public String getTypeName(int typeNumber) { return (String) menuMap.keySet().toArray()[typeNumber]; }

    public void drawStartMenu(){
        int i = 1;
        for (String typeName: menuMap.keySet()) {
            System.out.print(i + ". ");
            System.out.println(typeName);
            i++;
        }
    }

    public void drawProducts(String type) {
        int i = 1;
        for (Food product: menuMap.get(type)) {
            System.out.print(i + ". ");
            System.out.print(product.getName());
            System.out.println("    " + product.getCost() + " rub.");
            i++;
        }
    }

    public void drawDescription(int type, int food){
        String selectedType = menuMap.keySet().toArray(new String[0])[type];
        System.out.println("Price: " + menuMap.get(selectedType).get(food).getCost() + " rub.");
        System.out.println(menuMap.get(selectedType).get(food).getDescription());
    }

    public Food getFood(int type, int food) {
        String selectedType = menuMap.keySet().toArray(new String[0])[type];
        return menuMap.get(selectedType).get(food);
    }

    public int getTypeSize(String type){
        return menuMap.get(type).size();
    }
}
