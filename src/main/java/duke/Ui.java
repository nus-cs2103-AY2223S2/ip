package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * A UI build upon the chatting bot.
 */
public class Ui {

    private Scanner in;
    private PrintStream out;

    /**
     * The default constructor.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * The constructor of this class.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * The method that returns the user command.
     *
     * @return the user command
     */
    public String getUserCommand() {

        String input = in.nextLine();
        while (input == "") {
            out.println("OOPs, please enter a valid command.");
            input = in.nextLine();
        }
        return input;
    }

    /**
     * The method that prints the greetings.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println("Hello from\n" + logo);
        String greetings = "Hello! I'm Duke" + "\nWhat can I do for you?";
        out.println(greetings);
    }

    /**
     * The method that shows the messages responded by the bot.
     *
     * @param message
     */
    public void showMessage(String message) {
        out.println(message);
    }
}
