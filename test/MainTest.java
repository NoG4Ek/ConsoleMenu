import org.junit.jupiter.api.Assertions;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
            check.addFood(m.getFood(1, 1));

            Assertions.assertEquals(260, check.price());
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }
}
