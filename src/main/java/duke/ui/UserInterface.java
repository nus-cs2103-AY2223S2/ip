package duke.ui;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.tasks.TaskList;

/**
 * Manages the user interface of the application.
 * 
 * @author Samarth Verma
 */
public class UserInterface {
    private InputStream in;
    private PrintStream out;
    private Scanner sc;

    /**
     * Creates a new UserInterface object.
     */
    public UserInterface() {
        in = System.in;
        out = System.out;
        sc = new Scanner(in);
    }

    /**
     * Prints a line of underscores.
     */
    private void printLine() {
        out.println("____________________________________________________________");
    }

    /**
     * Prints a greeting message.
     */
    public void showGreeting() {
        printLine();
        out.println();
        out.println("Hello! I'm Duke");
        out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints an exit message
     */
    public void showExitMessage() {
        showMessage("Bye! Have a great day!");
    }

    /**
     * Prints a message.
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        out.println();
        out.println(message);
        printLine();
    }

    /**
     * Prints the prompt symbol.
     */
    public void showPrompt() {
        out.print("\n> ");
    }

    /**
     * Gets the input from the user.
     * @return The input from the user.
     */
    public String getInput() {
        showPrompt();
        String input = sc.nextLine();
        return input;
    }

    /**
     * Checks if the input has more lines
     * @return
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Prints the given list of tasks
     * @param list List which is printed
     */
    public void showTasks(TaskList list) {
        String message = list.stream().map(t -> String.format("%d. %s", t.id(), t.toString()))
                .collect(Collectors.joining("\n"));

        showMessage(message);
    }
}
