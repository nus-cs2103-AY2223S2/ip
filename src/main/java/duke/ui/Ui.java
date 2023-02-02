package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class represents Duke's command line user interface that interacts with the user.
 */
public class Ui {
    private static Ui instanceUi = new Ui();
    private Scanner sc;

    private Ui() {
        sc = new Scanner(System.in);
    }

    protected Ui(String input) {
        System.out.println(input);
    }

    /**
     * Returns the instance of Ui.
     *
     * @return An instance of Ui.
     */
    public static Ui getInstance() {
        return instanceUi;
    }

    // region Ui for output

    /**
     * Prints Duke's greetings.
     *
     * @returns Duke's greetings as a formatted String.
     */
    public String greet() {
        // @formatter:off
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        // @formatter:on
        String greetString = "Hello from\n" + logo + "\nWhat can I do for you?\n"
                + "---------------------\n\n";
        System.out.println(greetString);

        return greetString;
    }

    /**
     * Prints the partitions, ----, then prints the string in-between. \n is required for the end of the
     * string. <blockquote> ---------------------
     * <p>
     * your string here
     * <p>
     * --------------------- </blockquote>
     *
     * @param s The string in between the ---- partitions.
     * @return Resulting string with ---- partitions.
     */
    public String printWithPartition(String s) {
        String ouput = "---------------------\n" + s + "---------------------\n";

        System.out.println(ouput);

        return ouput;
    }

    /**
     * Prints the Tasks in Duke's list, including their done status.
     *
     * @param tasks The task list to print out.
     * @return The string of the tasklist that was printed out.
     */
    public String printList(TaskList tasks) {
        String ls = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            ls = ls + "\t" + Integer.toString(i + 1) + "." + temp.toString() + "\n";
        }
        return printWithPartition(ls);
    }

    /**
     * Prints the next line of input.
     *
     * @return The string of that was printed out.
     */
    public String printNextInput() {
        return printWithPartition("\tDuke: " + sc.nextLine().strip() + "\n");
    }

    /**
     * Prints the exception message as Duke.
     *
     * @param message The exception message.
     * @return The string of the printed out exception message.
     */
    public String printException(String message) {
        return printWithPartition("\t" + message + "\n");
    }

    /**
     * Prints Duke's goodbye message.
     *
     * @return The string of the printed out duke's goodbye.
     */
    public String printGoodbye() {
        return printWithPartition("\tGoodbye!\n");
    }

    // endregion

    // region Ui for input

    /**
     * Throws away a line of the input.
     *
     * <p>
     * Warning: May prompt user for a line of input if not used properly.
     */
    public void throwAwayInput() {
        sc.nextLine();
    }

    /**
     * Get a task number from the user.
     *
     * @return The task number.
     * @throws DukeException If the input is not a valid number.
     */
    public int getTaskNum() throws DukeException {
        try {
            String s = sc.nextLine().strip();
            int num = Integer.parseInt(s) - 1;
            return num;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a valid task number");
        }
    }

    /**
     * Gets the command from the user.
     *
     * @return The string containing the user's command.
     */
    public String getCommand() {
        return sc.next();
    }

    /**
     * Gets the name of a task from the user.
     *
     * @return The string containing the name of a task from the user.
     * @throws DukeException If the name is empty.
     */
    public String getName() throws DukeException {
        String name = sc.nextLine().strip();
        if (name == "") {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return name;
    }

    /**
     * Gets the name and date for a deadline task from the user.
     *
     * @return The string array containing the name and date for a deadline task.
     * @throws DukeException If the name and date are not found.
     */
    public String[] getDeadline() throws DukeException {
        try {
            String[] sorted = sc.nextLine().split(" /by ");
            String[] data = new String[] {sorted[0].strip(), sorted[1].strip()};
            return data;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(
                    "The deadline command should be used like this:\n" + "\tdeadline {name} /by {date}");
        }
    }

    /**
     * Gets the name, start and end date for an event task from the user.
     *
     * @return The string array containing the name, start and end date for an event task.
     * @throws DukeException If the name, start and end date are not found.
     */
    public String[] getEvent() throws DukeException {
        try {
            String[] line = sc.nextLine().split(" /from ", 2);
            String[] dates = line[1].split(" /to ", 2);
            String[] data = new String[] {line[0].strip(), dates[0].strip(), dates[1].strip()};
            return data;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        }

    }
    // endregion
}
