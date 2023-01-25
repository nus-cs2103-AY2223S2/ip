package duke;

import java.util.Scanner;

/**
 * User interface of the application.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Print the divider among multiple response.
     */
    protected void printHorizontalLine() {
        System.out.println("\t" + DIVIDER);
    }

    /**
     * Print the welcome message upon start of the application.
     */
    public void showWelcomeMessage () {
        printHorizontalLine();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        printHorizontalLine();
    }

    /**
     * Print the exit message when application terminate.
     */
    public void showGoodbyeMessage () {
        //closeScanner();
        printHorizontalLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }


    /**
     * Print response to the task command.
     *
     * @param taskList list that contain the task.
     */
    public void responseToListCommand (TaskList taskList) {
        printHorizontalLine();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            int tmp = i + 1;
            System.out.println("\t" + tmp + "." + taskList.getTaskAt(i));
        }
        printHorizontalLine();
    }

    /**
     * Print response to the add task command.
     *
     * @param task task to be added into task list.
     * @param taskList list that contain the task.
     */
    public void responseToLAddTaskCommand (Task task, TaskList taskList) {
        printHorizontalLine();
        System.out.println("\tGot it. I've added this task:\n\t  " + task + "\n\tNow you have "
                + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Print response to the mark command
     *
     * @param task task to be marked as done.
     */
    public void responseToMarkTaskCommand (Task task) {
        printHorizontalLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        printHorizontalLine();
    }

    /**
     * Print response to the unmark response.
     *
     * @param task task to be marked as undone.
     */
    public void responseToUnmarkTaskCommand (Task task) {
        printHorizontalLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        printHorizontalLine();
    }

    /**
     * Print response to delete command.
     *
     * @param taskList list that contained the task
     * @param index task to be deleted from task list.
     */
    public void responseToDeleteTaskCommand (TaskList taskList, int index) {
        printHorizontalLine();
        System.out.println("\tNoted. I've removed this task:\n\t  " + taskList.getTaskAt(index)
                + "\n\tNow you have " + (taskList.size() - 1) + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Print response to delete command.
     *
     * @param taskList list that contained the task
     */
    public void responseToFindTaskCommand(TaskList taskList) {
        printHorizontalLine();
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            System.out.println("\t" + index + "." + taskList.getTaskAt(i).toString());
        }
        printHorizontalLine();
    }

    /**
     * Print loading error
     * If the file given doesn't exit, it will print this error.
     */
    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("\t☹ OOPS!!! Unable to load data from the file");
        printHorizontalLine();
    }

    /**
     * Print the error caused in the program.
     *
     * @param msg error message to print.
     */
    public void showError(String msg) {
        printHorizontalLine();
        System.out.println("\t☹ OOPS!!! "+ msg);
        printHorizontalLine();
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return command entered by the user.
     */
    public String requestForUserInput() {
        System.out.println("\tEnter Command: ");
        return scanner.nextLine();
    }

    private void closeScanner() {
        scanner.close();
    }
}
