package book;

import java.util.Scanner;

import book.task.Task;

/**
 * Class that handles user interaction.
 */
public class Ui {
    /** ASCII art for {@code Book} logo. */
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <\n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    /** Line divider. */
    private static final String LINE =
            "________________________________________________________________________________";
    /** Indentation. */
    private static final String INDENT = "    ";
    /** {@code Scanner} instance for reading user input. */
    private static final Scanner input = new Scanner(System.in);

    /**
     * Returns user input as a {@code String}.
     * @return {@code String} of user input.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Prints the {@code LINE} divider.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message for {@code Book}.
     */
    public void showWelcome() {
        System.out.println("Good day! This is\n" + LOGO + "What may I help you with?");
    }

    /**
     * Prints the exit message for {@code Book}.
     */
    public void showExit() {
        System.out.println("Bye! Pick up Book again soon!");

    }

    /**
     * Prints the message for adding a {@code Task} to {@code Book}.
     * @param task {@code Task} associated with the message.
     */
    public void showAdd(Task task) {
        System.out.println("Understood, adding\n" + INDENT + task + "\nto Book.");
    }

    /**
     * Prints the message for deleting a {@code Task} from {@code Book}.
     * @param task {@code Task} associated with the message.
     */
    public void showDelete(Task task) {
        System.out.println("Acknowledged, striking the following task off of Book's pages:\n"
                + INDENT + task);
    }

    /**
     * Prints the message for marking a {@code Task} from {@code Book}.
     * @param task {@code Task} associated with the message.
     */
    public void showMark(Task task) {
        System.out.println("The following task has been marked complete:\n" + INDENT + task);
    }

    /**
     * Prints the message for unmarking a {@code Task} from {@code Book}.
     * @param task {@code Task} associated with the message.
     */
    public void showUnmark(Task task) {
        System.out.println("The following task has been marked incomplete:\n" + INDENT + task);
    }

    /**
     * Prints the message for showing the size of the {@code TaskList} in {@code Book}.
     * @param list {@code TaskList} associated with the message.
     */
    public void showTaskListSize(TaskList list) {
        System.out.println(list.listSize() + " task(s) exist in Book's pages.");
    }

    /**
     * Prints the message for showing the {@code TaskList} in {@code Book}.
     * @param list {@code TaskList} associated with the message.
     */
    public void showList(TaskList list) {
        System.out.println("Here are the tasks stored in Book:");
        System.out.print(list);
    }

    /**
     * Prints the message for showing errors in the execution of {@code Book}.
     * @param message {@code String} message associated with the error.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
