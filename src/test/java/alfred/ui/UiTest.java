package alfred.ui;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final Ui ui = new Ui();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream ps = new PrintStream(outputStream);
    private final PrintStream consoleStream = new PrintStream(new FileOutputStream(FileDescriptor.out));

    @Test
    public void displayLogo() {
        System.setOut(ps);
        ui.displayLogo();
        String actual = outputStream.toString();
        String test = " _____ __     ______ _____ ____ ___ "
                + "\n" + "|  -  |  |   |  ____|  _  |  __| _ \\     "
                + "\n" + "| | | |  |   | |___ | |_|_| |__|| | |  "
                + "\n" + "|  -  |  |___|  ___||  _ \\  |__||_| |"
                + "\n" + "|_| |_| ____ |__|   |_| \\_|____|__ /   "
                + "\n";
        assertEquals(actual, test);
        System.setOut(consoleStream);
    }

    @Test
    public void displayLine() {
        System.setOut(ps);
        ui.displayLine();
        String actual = outputStream.toString();
        String test = "    ____________________________________________________________"
                + "\n";
        assertEquals(actual, test);
        System.setOut(consoleStream);
    }

    // can we use methods after we test? displayLine
    @Test
    public void displayCommand_emptyString() {
        System.setOut(ps);
        ui.displayCommand("");
        String actual = outputStream.toString();
        outputStream.reset();

        ui.displayLine();
        System.out.println("    " + "");
        ui.displayLine();
        String test = outputStream.toString();

        assertEquals(actual, test);
        System.setOut(consoleStream);
    }

    @Test
    public void displayCommand_nonEmptyString() {
        System.setOut(ps);
        ui.displayCommand("Hello World!");
        String actual = outputStream.toString();
        outputStream.reset();

        ui.displayLine();
        System.out.println("    " + "Hello World!");
        ui.displayLine();
        String test = outputStream.toString();

        assertEquals(actual, test);
        System.setOut(consoleStream);
    }

    // test displayError need stub for AlfredError?
}
