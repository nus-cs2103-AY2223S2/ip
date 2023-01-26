package duke;

import duke.tasks.*;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the user input
     *
     * @return the string input
     */
    public String getUserInput() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner object
     */
    public void closeInput() {
        this.sc.close();
    }

    /**
     * Prints a line
     */
    public void printLine() {
        System.out.println("------------------------------------------------------------------");
    }

    /**
     * Displays the initial message
     */
    public void initialDisplay() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Displays text after adding task
     *
     * @param task the task object
     * @param listLength the length of the TaskList
     */
    public void taskAddDisplay(Task task, int listLength) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task.toString());
        displayTasks(listLength);
    }

    /**
     * Displays text after deleting task
     *
     * @param list the TaskList storing all the tasks
     * @param taskIndex the index of task to be deleted
     */
    public void taskDeleteDisplay(TaskList list, int taskIndex) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + list.getTask(taskIndex).toString());
    }

    /**
     * Prints the list
     *
     * @param list the TaskList storing the Task objects
     */
    public void displayList(TaskList list) {
        int listSize = list.listLength();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + list.getTask(i).toString());
        }
    }

    /**
     * Prints the number of tasks in the TaskList
     *
     * @param listLength the length of the TaskList
     */
    public void displayTasks(int listLength) {
        System.out.println(String.format("\tNow you have %d tasks in the list.", listLength));
    }

    /**
     * Marks the task as incomplete
     *
     * @param task the Task object
     */
    public void unmarkTaskDisplay(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Marks the task as complete
     *
     * @param task the Task object
     */
    public void markTaskDisplay(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Displays text after exit message
     */
    public void exitDisplay() {
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints text if no matches found in the list
     */
    public void noMatchFoundDisplay() {
        System.out.println("\tNo matches found...");
    }

    /**
     * Prints list if match is found
     * @param list the TaskList storing the Task objects
     */
    public void matchFoundDisplay(TaskList list) {
        System.out.println("\tHere are the matching tasks in your list:");
        displayList(list);
    }
}
