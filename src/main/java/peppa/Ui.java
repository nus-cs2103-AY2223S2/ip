package peppa;

import java.util.ArrayList;
import java.util.Scanner;

import peppa.commands.DeadlineCommand;
import peppa.commands.DeleteCommand;
import peppa.commands.EventCommand;
import peppa.commands.ExitCommand;
import peppa.commands.ListCommand;
import peppa.commands.MarkCommand;
import peppa.commands.TodoCommand;
import peppa.commands.UnmarkCommand;

/**
 * Represents a user interface screen for reading in user inputs and displaying messages in terminal.
 */
public class Ui {
    public static final String DIVIDER = "=============================================";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints custom message upon a successful add task operation.
     *
     * @param task Task that was added.
     */
    public static void displayAddTaskMessage(Task task) {
        System.out.println("Oink! I've added the following task:");
        System.out.println("> " + task.toString());
    }

    /**
     * Prints list of commands that the chatbot currently supports.
     */
    public static void displayCommands() {
        System.out.println("> " + ListCommand.COMMAND_WORD + "\n"
                + "> " + MarkCommand.COMMAND_WORD + "\n"
                + "> " + UnmarkCommand.COMMAND_WORD + "\n"
                + "> " + TodoCommand.COMMAND_WORD + "\n"
                + "> " + DeadlineCommand.COMMAND_WORD + "\n"
                + "> " + EventCommand.COMMAND_WORD + "\n"
                + "> " + DeleteCommand.COMMAND_WORD + "\n"
                + "> " + ExitCommand.COMMAND_WORD);
    }

    /**
     * Prints custom message upon a successful delete task operation.
     *
     * @param task Task that was deleted.
     */
    public static void displayDeleteTaskMessage(Task task) {
        System.out.println("Oink! I've removed the following task:");
        System.out.println("> " + task.toString());
    }

    /**
     * Prints any user-provided message.
     *
     * @param message Message to display.
     */

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints custom message upon a successful mark task as done operation.
     *
     * @param task Task that was marked as done.
     */
    public static void displayMarkDoneMessage(Task task) {
        System.out.println("Oink! I've marked this task as done:");
        System.out.println("> " + task.toString());
    }

    public static void displayMatchingTasks(ArrayList<Task> tasks) {
        System.out.println("Oink! Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the current tasklist, including the details of each task (e.g. id and description).
     *
     * @param tasks List of tasks.
     */
    public static void displayTaskList(TaskList tasks) {
        System.out.println("Oink! Here are the tasks in your list currently:");
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println((i + 1) + ". " + tasks.retrieveTask(i));
        }
    }

    /**
     * Prints the number of tasks in the list currently.
     *
     * @param tasks List of tasks.
     */
    public static void displayTaskSummary(TaskList tasks) {
        System.out.println("You now have " + tasks.getLength() + " tasks in the list.");
    }

    /**
     * Prints custom message upon a successful unmark task as done operation.
     *
     * @param task Task that was unmarked as done.
     */
    public static void displayUnmarkDoneMessage(Task task) {
        System.out.println("Oink! I've marked this task as undone:");
        System.out.println("> " + task.toString());
    }

    /**
     * Prints the introductory greeting message upon initialisation of the chatbot.
     */
    public static void greetUser() {
        String logo = " ____  ____  ____  ____   __\n"
                + "(  _ \\(  __)(  _ \\(  _ \\ / _\\\n"
                + " ) __/ ) _)  ) __/ ) __//    \\\n"
                + "(__)  (____)(__)  (__)  \\_/\\_/\n";
        String art = "       _\n"
                + "  <`--'\\>______\n"
                + "  /. .  `'     \\\n"
                + " (`')  ,        @\n"
                + "  `-._,        /\n"
                + "     )-)_/--( >  jv\n"
                + "    ''''  ''''\n";
        System.out.println(DIVIDER);
        System.out.println("Oink! I'm\n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println(DIVIDER);
    }

    /**
     * Prints divider line for nicer formatting.
     */
    public static void insertDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints farewell message upon leaving the chatbot.
     */
    public void terminateSession() {
        System.out.println("Oink oink! See you again :)");
        this.sc.close();
    }

    /**
     * Reads and returns the command entered by the user.
     *
     * @return User-provided input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}
