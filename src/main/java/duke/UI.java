package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";
    private static final String INDENTATION = " ".repeat(5);
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints a message to the output.
     *
     * @param msg the message to be printed to the output
     */
    public static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n")).map(line -> INDENTATION + line)
                .collect(Collectors.joining("\n"));
        System.out.println(LINE);
        System.out.println(displayedMsg);
        System.out.println(LINE);
    }

    /**
     * Prints the greeting message when the application starts.
     */
    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads the next command from the user.
     *
     * @return a string representing the command
     */
    public static String readCommand() {
        return sc.nextLine();
    }
}
