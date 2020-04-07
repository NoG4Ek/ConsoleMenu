import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainTest {

    @org.junit.jupiter.api.Test
    void main() throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        String [] args={"-p","Menu.txt"};
        Main.main(args);

        System.out.flush();
        System.setOut(old);
        System.out.println("+"+baos.toString());
    }
}
