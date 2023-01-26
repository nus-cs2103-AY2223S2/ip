package duke.ui;

import java.util.Scanner;

import duke.task.TaskList;

/**
 * Ui handles all interaction with the user.
 *
 * @author      Oskar Lew
 * @version     0.1
 * @since       0.1
 */
public class Ui {
    private String LOGO_AND_GREETINGS = "Hello from\n" 
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?";
    private Scanner scanner;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints to console the greeting message.
     */
    public void printGreetings() {
        System.out.println(LOGO_AND_GREETINGS);
    }

    /**
     * Returns the user input as a String.
     *
     * @return echo The line entered by the user.
     */
    public String getLine() {
        String echo = "";
        if (scanner.hasNext()) {
            echo = scanner.nextLine();
        }
        return echo;
    }

    /**
     * Prints to console the goodbye message.
     */
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints to console the current task list.
     * @param tasks The list of tasks.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Method that prints to console the tasks
     * with descriptions that contains the
     * targeted word.
     * @param tasks List of tasks.
     */
    public void showListWithMatchedWords(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Method to print to console when there
     * are no tasks with descriptions that contains
     * the targeted word.
     * @param word The targeted word.
     */
    public void noMatchingWords(String word) {
        System.out.printf("There are no task that contains the word: %s", word);
    }

    /**
     * Method to print to console an error
     * message when there is no valid word
     * after the find command.
     */
    public void findError() {
        System.out.println("☹ OOPS!!! The keyword is no valid!");
    }

    /**
     * Prints to console the task that was marked.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task in the task list.
     */
    public void markMsg(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    /**
     * Prints to console error message when
     * there is no/an invalid number
     * after the mark command.
     */
    public void markError() {
        System.out.println("☹ OOPS!!! A valid number has to follow the mark command");
    }

    /**
     * Prints to console the task that was unmarked.
     * @param tasks The list of tasks.
     * @param index The index of task in the task list.
     */
    public void unmarkMsg(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
    }

    /**
     * Prints to console error message when
     * there is no/an invalid number
     * after the unmark command.
     */
    public void unmarkError() {
        System.out.println("☹ OOPS!!! A valid number has to follow the unmark command");
    }

    /**
     * Prints to console a message to indicate that
     * a task was added to the task list.
     *
     * @param tasks The list of tasks.
     */
    public void addMsg(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Prints to console a message to indicate that
     * a task was removed from the task list.
     * @param tasks The list of tasks
     * @param index The index of the task in the task list.
     */
    public void deleteMsg(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size() - 1);
    }

    /**
     * Prints to console an error message when
     * there is no description after the todo command.
     * */
    public void todoError() {
        System.out.println("☹ OOPS!!! The description cannot be empty!");
    }

    /**
     * Prints to console an error message when
     * there is no description or timing after the
     * event command.
     */
    public void eventError() {
        System.out.println("☹ OOPS!!! The timing was not specified or there was no description!");
    }

    /**
     * Prints to console an error message when
     *  there is no description or timing after
     *  the deadline command.
     */
    public void deadlineError() {
        System.out.println("☹ OOPS!!! The timing needs to be in format yyyy-mm-dd hhmm"
                + " or there was no description!");
    }

    /**
     * Prints to console an error message when
     * there is no number after the delete command.
     */
    public void deleteError() {
        System.out.println("☹ OOPS!!! A valid number has to follow the delete command.");
    }

    /**
     * Prints to console an error message when
     * an unknown command is parsed.
     */
    public void unknownMsg() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Method to close the scanner
     * after the user ends the program.
     */
    public void endUi() {
        scanner.close();
    }
}
