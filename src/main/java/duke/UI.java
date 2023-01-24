package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";
    private static final String INDENTATION = " ".repeat(5);
    private static final Scanner sc = new Scanner(System.in);

    public static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n")).map(line -> INDENTATION + line)
                .collect(Collectors.joining("\n"));
        System.out.println(LINE);
        System.out.println(displayedMsg);
        System.out.println(LINE);
    }

    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static String readCommand() {
        return sc.nextLine();
    }

    public static void echoError(DukeException ex) {
        echo(ex.getMessage());
    }
}
