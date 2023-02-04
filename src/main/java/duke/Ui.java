package duke;

import java.util.Scanner;

import duke.task.TaskList;

/**
 * Handles all Ui displaying and user input.
 */
public class Ui {
    private Scanner sc;
    /**
     * Constructor for Ui class.
     * This object can be used for all Ui purposes, primarily printing messages and reading of user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the starting logo for duke.Duke.
     */
    public void displayIntro() {
        String logo = " ___________________\n"
                + " | _______________ |\n"
                + " | |             | |\n"
                + " | |     MEL     | |\n"
                + " | |     <3      | |\n"
                + " | |   CS2103    | |\n"
                + " | |_____________| |\n"
                + " |_________________|\n"
                + "     _[_______]_\n"
                + " ___[___________]___\n"
                + "|         [_____] []|__\n"
                + "|         [_____] []|  \\__\n"
                + "L___________________J     \\ \\___\\/\n"
                + " ___________________      / \\\n"
                + "/###################\\    (__)\n";
        System.out.println(logo + "Hello! I'm MEL\nWhat can I do for you?\n-----------------------");
    }

    /**
     * Displays goodbye message.
     */
    public void displayBye() {
        System.out.println("MEL: Bye. Hope to see you again soon!");
    }

    /**
     * Reads user input and returns as string.
     *
     * @return User input as String.
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Displays '>' to indicate user can input.
     */
    public void displayRepeatedBlank() {
        System.out.print("> ");
    }

    /**
     * Displays message for invalid user input.
     */
    public void displayInvalidInputFormat() {
        System.out.println("MEL: Invalid format of input, please try again!");
    }

    /**
     * Displays message for invalid user input due to integer type.
     */
    public void displayInvalidIntFormat() {
        System.out.println("MEL: Invalid integer, please try again!");
    }

    /**
     * Displays message for invalid user input due to date/time from & to format.
     */
    public void displayInvalidFromToFormat() {
        System.out.println("Invalid format, please try again using [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]");
    }

    /**
     * Displays message for invalid user input due to date/time by format.
     */
    public void displayInvalidByFormat() {
        System.out.println("Invalid format, please try again using [task] /by [YYYY-MM-DD]");
    }

    /**
     * Displays message to inform user that task has been added.
     *
     * @param taskName Task name that was added.
     * @param size Task list size.
     */
    public void displayAdded(String taskName, int size) {
        System.out.println("Got it. I've added this task:\n" + taskName);
        displayTotalNumList(size);
    }

    /**
     * Displays message to inform user that task has been deleted.
     *
     * @param taskName Task name that was deleted.
     */
    public void displayDeleted(String taskName) {
        System.out.println("Noted. I've removed this task:\n" + taskName);
    }

    /**
     * Displays how many tasks is currently in the list.
     *
     * @param size Task list size.
     */
    public void displayTotalNumList(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays all tasks in the list.
     *
     * @param list Task list.
     */
    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    /**
     * Displays the marked task.
     *
     * @param taskMarked Name of task that was marked.
     */
    public void displayMarked(String taskMarked) {
        System.out.println("Nice! I've marked this task as done:\n" + taskMarked);
    }

    /**
     * Displays the unmarked task.
     *
     * @param taskUnmarked Name of task that was marked.
     */
    public void displayUnmarked(String taskUnmarked) {
        System.out.println("OK, I've marked this task as not done yet:\n" + taskUnmarked);
    }

    /**
     * Displays the results that contains search input.
     *
     * @param list Task list to search through.
     * @param searchInput String input to search substring.
     */
    public void displayResults(TaskList list, String searchInput) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(searchInput)) {
                System.out.println(count + "." + list.get(i));
                count++;
            }
        }
    }
}
