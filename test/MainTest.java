import org.junit.jupiter.api.Assertions;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

public class MainTest {

    @org.junit.jupiter.api.Test
    void main() {
        String [] args={"-p","Menu.txt"};

        Menu m = new Menu();

        CmdLineParser parser = new CmdLineParser(m);
        try {
            parser.parseArgument(args);
            m.assemble();

            Check check = new Check();

            //Check
            check.addFood(m.getFood(1, 1));
            Assertions.assertEquals(260, check.price());

            check.addFood(m.getFood(1, 1));
            Assertions.assertEquals(520, check.price());

            check.deleteFood(0);
            check.deleteFood(0);
            check.addFood(m.getFood(3, 2));
            Assertions.assertEquals(1720, check.price());

            //Menu
            Assertions.assertEquals(9, m.getMapSize());
            Assertions.assertEquals("Гарниры", m.getTypeName(1));
            Assertions.assertEquals(5, m.getTypeSize("Гарниры"));
            Assertions.assertEquals(new Food("Сельдь \"под стопочку\"", "Холодные закуски",
                    470, "Филе сельди с отварным картофелем, соленым огурцом, луком, " +
                    "ароматным маслом и зеленью."), m.getFood(3, 0));

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }
}
