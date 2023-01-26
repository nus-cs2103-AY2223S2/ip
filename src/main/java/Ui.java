import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * A UI build upon chatbot
 */
public class Ui {

    private Scanner in;
    private PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {

        String input = in.nextLine();
        while (input == "") {
            out.println("OOPs, please enter a valid command.");
            input = in.nextLine();
        }
        return input;
    }

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

    public void showMessage(String message) {
        out.println(message);
    }
}
