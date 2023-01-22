package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
        String welcome = "Hello from\n"
                + " ____       _\n"
                + "|  _ \\ _  _| | ____ _\n"
                + "| | | | | |  | |/ / _ \\\n"
                + "| |_| | |_|  |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?";
        println(welcome);
    }

    /**
     * Prints dividers.
     */
    public void showLine() {
        println("-".repeat(40));
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
     * System.out inputs line by line.
     *
     * @param lines {@link String} array
     */
    public void println(Object... lines) {
        for (Object l : lines) {
            out.println(l.toString());
        }
    }

    /**
     * System.out inputs line by line with error indicator.
     *
     * @param lines {@link String} array
     */
    public void showError(Object... lines) {
        for (Object l : lines) {
            out.println(l.toString());
            println("Error! [ " + l + " ]");
        }
    }
}
