package duke;

import java.util.Scanner;

/**
 * User interface of the application.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Return the divider among multiple response.
     *
     * @return horizontal line.
     */
//    protected String printHorizontalLine() {
//        return "\t" + DIVIDER;
//    }

    /**
     * Return the welcome message upon start of the application.
     *
     * @return welcome message
     */
    public String showWelcomeMessage() {
        String msg = "Hello! I'm Duke\n" + "What can I do for you?";
        return  msg;
    }

    /**
     * Return the exit message when application terminate.
     *
     * @return goodbye message.
     */
    public String showGoodbyeMessage() {
        String msg = "Bye. Hope to see you again soon!";
        return msg;
    }

    /**
     * Return response to the task command.
     *
     * @param taskList list that contain the task.
     * @return message for list command.
     */
    public String responseToListCommand(TaskList taskList) {
        String msg = "Here are the tasks in your list:";
        for(int i = 0; i < taskList.size(); i++) {
            int tmp = i + 1;
            msg += "\n" + tmp + "." + taskList.getTaskAt(i);
        }
        return msg;
    }


    /**
     * Return response to the add task command.
     *
     * @param task task to be added into task list.
     * @param taskList list that contain the task.
     * @return message for add task command.
     */
    public String responseToLAddTaskCommand(Task task, TaskList taskList) {
        String msg = "Got it. I've added this task:\n  " + task + "\nNow you have "
                + taskList.size() + " tasks in the list.";
        return msg;
    }


    /**
     * Return response to the mark command
     *
     * @param task task to be marked as done.
     * @return message for mark command.
     */
    public String responseToMarkTaskCommand(Task task) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + task;
        return msg;
    }

    /**
     * Return response to the unmark response.
     *
     * @param task task to be marked as undone.
     * @return message for unmark command.
     */
    public String responseToUnmarkTaskCommand(Task task) {
        String msg = "OK, I've marked this task as not done yet:\n";
        msg += "  " + task;
        return msg;
    }

    /**
     * Return response to delete command.
     *
     * @param taskList list that contained the task
     * @param index task to be deleted from task list.
     * @return message for delete command.
     */
    public String responseToDeleteTaskCommand(TaskList taskList, int index) {
        String msg = "Noted. I've removed this task:\n  " + taskList.getTaskAt(index)
                + "\nNow you have " + (taskList.size() - 1) + " tasks in the list.";
        return msg;
    }

    /**
     * Return response to delete command.
     *
     * @param taskList list that contained the task
     * @return message for find task command.
     */
    public String responseToFindTaskCommand(TaskList taskList) {
        String msg = "";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            msg += "\n" + index + "." + taskList.getTaskAt(i).toString();
        }
        return msg;
    }

    /**
     * Return loading error message
     * If the file given doesn't exit, it will print this error.
     *
     * @return message for loading error.
     */
    public String showLoadingError() {
        String msg = "OOPS!!! Unable to load data from the file";
        return msg;
    }

    /**
     * Return the error caused in the program.
     *
     * @param errorMsg error message to print.
     * @return error message.
     */
    public String showError(String errorMsg) {
        String msg = "OOPS!!! "+ errorMsg;
        return msg;
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return command entered by the user.
     */
    public String requestForUserInput() {
        System.out.println("\tEnter Command: ");
        return SCANNER.nextLine();
    }

    private void closeScanner() {
        SCANNER.close();
    }
}
