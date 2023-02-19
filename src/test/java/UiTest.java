import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import shigure.Cli;
import shigure.Ui;

public class UiTest {
    @Test
    public void printIntro_ascii() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        Ui ui = new Cli(true);
        ui.printIntro();
        String out = outStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals(5, out.codePoints().filter(c -> c == '\n').count());
    }

    @Test
    public void printIntro() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        Ui ui = new Cli(false);
        ui.printIntro();
        String out = outStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals(5, out.codePoints().filter(c -> c == '\n').count());
    }

    @Test
    public void printRead_ascii() {
        ByteArrayInputStream inStream = new ByteArrayInputStream("Input text".getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        Cli ui = new Cli(false);
        ui.printMiki("hello world");
        ui.printDiv();
        ui.printMiki("mint fantôme");

        String in = ui.readLine();
        String out = outStream.toString().replace("\r\n", "\n");

        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("Input text", in);
        assertEquals("     hello world\n"
                        + "    ____________________________________________________________________________\n"
                        + "     mint fantôme\n"
                        + ">",
                out);
    }

    //@Test
    public void printRead() {
        ByteArrayInputStream inStream =
                new ByteArrayInputStream("Input text \u29CD\u23E7\u2A33\u2A7C\u299E\u2A04\u2B48".getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        Cli ui = new Cli(false);
        ui.printMiki("hello world");
        ui.printMiki("\u6587\u6708\u307F\u304D x fantôme");
        ui.printDiv();
        ui.printMiki("\u225D\u237C\u2A50\u2A69\u2368\u2118");

        String in = ui.readLine();
        String out = outStream.toString();

        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("Input text \u29CD\u23E7\u2A33\u2A7C\u299E\u2A04\u2B48", in);
        assertEquals("    hello world\n"
                        + "    \u6587\u6708\u307F\u304D x fantôme\n"
                        + "    ____________________________________________________________________________"
                        + "    \u225D\u237C\u2A50\u2A69\u2368\u2118\n"
                        + ">",
                out);
    }
}
