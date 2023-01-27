package duke.ui;

import duke.task.Task;

/**
 * A UI class handling all user interaction.
 */
public class UI {
    protected String indent = "     ";
    protected String divider = indent + "____________________________________________________________";

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println(divider);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(divider);
    }

    /**
     * Prints a formatted message.
     */
    public void printMessage(String message) {
        System.out.println(divider + "\n" + indent + message + "\n" + divider);
    }

    /**
     * Prints a success message.
     */
    public void printSuccessMessage(String message, Task task) {
        printMessage(message + "\n" + indent + task.toString());
    }

    /**
     * Prints a task message.
     */
    public void printTaskMessage(String message, Task task, int numOfTasks) {
        printMessage(message + "\n" +
                indent + indent + task.toString() + "\n" +
                indent + "Now you have " + numOfTasks + " task(s) in the list.");
    }
}