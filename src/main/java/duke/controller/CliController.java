package duke.controller;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.model.Model;
import duke.util.container.ExecutionResult;

/**
 * Controller of the cli-based version of the application.
 * <p>
 * All methods in this class are static methods, as there is no need to create multiple instances of
 * {@code CliController}. Singleton pattern is unnecessary here.
 */
public class CliController {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";
    private static final String INDENTATION = " ".repeat(5);
    private static final Scanner sc = new Scanner(System.in);
    private static Model model = null;
    private static boolean exitStatus = false;

    private CliController() {}

    /**
     * Sets the model that controls the logic of the application.
     *
     * @param model the model to be used by this controller
     */
    public static void setModel(Model model) {
        CliController.model = model;
    }

    /**
     * Prints a message to the output.
     *
     * @param msg the message to be printed to the output
     */
    private static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n"))
                .map(line -> INDENTATION + line)
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
     * Waits for the next input from the user, and handles that input.
     */
    public static void handleUserInput() {
        String input = sc.nextLine();
        ExecutionResult result = model.execute(input);
        echo(result.getMessage());
        exitStatus = result.getExitStatus();
    }

    /**
     * Checks the exit status of the application.
     *
     * @return {@code true} if the application should be terminated (after the user enters a "bye"
     *         command), otherwise {@code false}
     */
    public static boolean shouldExit() {
        return exitStatus;
    }
}
