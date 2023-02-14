package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents a duke.Ui object that handles the user interface.
 */
public class Ui {

    private final Scanner SC; // scanner to read user input.
    public Ui() {
        this.SC = new Scanner(System.in);
    }

    /**
     * @param txt text to indent.
     * @return indented string.
     */
    private static String autoIndent(String txt) {
        return "    " + txt.replace("\n", "\n    ");
    }

    /**
     * prints border line.
     */
    private static void borderLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    /**
     * Prints output in a nice format. (adds borders and indentation).
     *
     * @param inp input string.
     */
    public void prettifyOut(String inp) {
        borderLine();
        System.out.println(autoIndent(inp));
        borderLine();
    }

    /**
     * @return the command entered by the user.
     */
    public String readCommand() {
        return this.SC.nextLine();
    }

    /**
     * Prints border line.
     */
    public void printBorderLine() {
        borderLine();
    }


    /**
     * Prints out the Greeting message.
     */
    public void printGreeting() {
        String GREETING = "Hello! I'm duke.Duke\nWhat can I do for you?";
        prettifyOut(GREETING);
    }

    /**
     * Prints out the Goodbye message.
     */
    public void printGoodbye() {
        String GOODBYE = "Bye. Hope to see you again soon!";
        prettifyOut(GOODBYE);
    }

    /**
     * Prints the task list.
     * @param taskList The task list to be printed.
     */
    public void printTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            prettifyOut("You have no tasks in your list.");
        } else {
            prettifyOut("Here are the tasks in your list:\n" + taskList);
        }
    }

    /**
     * Prints the filtered task list.
     * @param taskList The filtered task list to be printed.
     */
    public void printTasksFound(TaskList taskList) {
        if (taskList.isEmpty()) {
            prettifyOut("No tasks in list matching the keyword.");
        } else {
            prettifyOut("Here are the matching tasks in your list:\n" + taskList);
        }
    }

    /**
     * Prints out the error message when loading file.
     */
    public void showLoadingError() {
        String ERROR = "Error loading file";
        prettifyOut(ERROR);
    }

    /**
     * Prints the message from DukeException.
     * @param e DukeException to be printed.
     */
    public void printDukeException(DukeException e) {
        prettifyOut(e.getMessage());
    }

    /**
     * @param task The task to be printed.
     * @param size The size of the task list after addition.
     */
    public void printTaskAdded(Task task, int size) {
        prettifyOut("Got it. I've added this task:\n  " + task + "\nNow you have " + size +
                " tasks in the list.");
    }

    /**
     * @param task The task that was marked.
     */
    public void printTaskMarked(Task task) {
        prettifyOut("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * @param task The task that was unmarked.
     */
    public void printTaskUnmarked(Task task) {
        prettifyOut("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * @param task The task that was deleted.
     * @param size The size of the task list after deletion.
     */
    public void printTaskDeleted(Task task, int size) {
        prettifyOut("Noted. I've removed this task:\n  " + task + "\nNow you have " + size +
                " tasks in the list.");
    }
}
