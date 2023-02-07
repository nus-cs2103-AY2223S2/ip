package duke.controller;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.model.ExecutionResult;
import duke.model.Model;

public class CliController {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";
    private static final String INDENTATION = " ".repeat(5);
    private static final Scanner sc = new Scanner(System.in);
    private static Model model = null;
    private static boolean exitStatus = false;

    private CliController() {}

    public static void setModel(Model model) {
        CliController.model = model;
    }

    /**
     * Prints a message to the output.
     *
     * @param msg the message to be printed to the output
     */
    private static void echo(String msg) {
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

    public static void handleUserInput() {
        String input = sc.nextLine();
        ExecutionResult result = model.execute(input);
        echo(result.getMessage());
        exitStatus = result.getExitStatus();
    }

    public static boolean shouldExit() {
        return exitStatus;
    }
}
