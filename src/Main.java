import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Scanner;

public class Main {
    private enum State {
        STOP,
        START_MENU,
        TYPE_MENU,
        PRODUCT,
        CHECK;
    }

    public static void main(String[] args) throws Exception {
        Menu m = new Menu();

        CmdLineParser parser = new CmdLineParser(m);
        try {
            parser.parseArgument(args);
            m.assemble();
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }

        Check c = new Check();
        Scanner sc = new Scanner(System.in);
        String str;
        State state = State.START_MENU;
        int lastType = 0;
        int lastFood = 0;

        //m.assemble();

        while (state != State.STOP){
            switch (state){
                case START_MENU:
                    System.out.println("~~ MENU ~~");
                    m.drawStartMenu();
                    str = sc.nextLine();
                    switch (str){
                        case "0":
                            state = State.STOP;
                            break;
                        case "Order":
                            state = State.CHECK;
                            break;
                        default:
                            if (str.matches("\\d") && 0 < Integer.parseInt(str) &&
                                    Integer.parseInt(str) <= m.getMapSize()) {
                                state = State.TYPE_MENU;
                                lastType = Integer.parseInt(str) - 1;
                            } else {
                                System.out.println("Invalid food type, try again");
                            }
                            break;
                    }
                    break;
                case TYPE_MENU:
                    System.out.println("~~ " + m.getTypeName(lastType) + " ~~");
                    m.drawProducts(m.getTypeName(lastType));
                    str = sc.nextLine();
                    switch (str){
                        case "0":
                            state = State.START_MENU;
                            break;
                        case "Order":
                            state = State.CHECK;
                            break;
                        default:
                            if (str.matches("\\d") && 0 < Integer.parseInt(str) &&
                                    Integer.parseInt(str) <= m.getTypeSize(m.getTypeName(lastType))) {
                                state = State.PRODUCT;
                                lastFood = Integer.parseInt(str) - 1;
                            } else {
                                System.out.println("Invalid food number, try again");
                            }
                            break;
                    }
                    break;
                case PRODUCT:
                    System.out.println("~~ " + m.getFood(lastType, lastFood).getName() + " ~~" + System.lineSeparator() +"Description:");
                    m.drawDescription(lastType, lastFood);
                    System.out.println(System.lineSeparator() + "\"Buy\" - add this product to your order");
                    str = sc.nextLine();
                    switch (str){
                        case "0":
                            state = State.TYPE_MENU;
                            break;
                        case "Buy":
                            c.addFood(m.getFood(lastType, lastFood));
                            System.out.println("Product successfully added!");
                            state = State.TYPE_MENU;
                            break;
                        case "Order":
                            state = State.CHECK;
                            break;
                        default:
                            System.out.println("Input error, try again");
                            break;
                    }
                    break;
                case CHECK:
                    System.out.println("~~ Your order ~~");
                    System.out.println(c.toString());
                    System.out.println("~~ Total ~~");
                    System.out.println(c.price() + " rub.");
                    System.out.println("Is that all right? (\"Yes\" / \"0\"-Back / \"Food number\"-Delete from order)");
                    str = sc.nextLine();
                    switch (str) {
                        case "0":
                            state = State.START_MENU;
                            break;
                        case "Yes":
                            System.out.println("~~ Thank you for your purchase! ~~");
                            state = State.STOP;
                            break;
                        default:
                            if (str.matches("\\d") && 0 < Integer.parseInt(str) &&
                                    Integer.parseInt(str) <= c.getShopListSize()) {
                                c.deleteFood(Integer.parseInt(str) - 1);
                            } else {
                                System.out.println("Input error, try again");
                            }
                            break;
                    }
                    break;
            }
        }
    }
}

