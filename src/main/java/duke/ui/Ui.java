package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.constant.Message;

/**
 * Handles UI related operations.
 */
public class Ui {

    private Scanner in;
    private PrintStream out;

    /**
     * Default constructor that uses System.in/out.
     *
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for self provided in/out channels.
     *
     * @param in  {@link InputStream} object
     * @param out {@link PrintStream} object
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints welcome messages.
     */
    public void showWelcome() {
        String welcome = Message.WELCOME;
        printConsole(welcome);
    }

    /**
     * Prints dividers.
     */
    public void showLine() {
        printConsole("-".repeat(40));
    }

    /**
     * Reads from System.in.
     *
     * @return {@link String} object
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * System.outs inputs line by line.
     *
     * @param lines {@link String} array
     */
    public void printConsole(Object... lines) {
        for (Object l : lines) {
            out.println(l.toString());
        }
    }

    /**
     * System.outs inputs line by line with error indicator.
     *
     * @param lines {@link String} array
     */
    public void showError(Object... lines) {
        for (Object l : lines) {
            printConsole("[Error!] " + l);
        }
    }
}
