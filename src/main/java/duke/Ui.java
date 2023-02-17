package duke;

import duke.task.Task;

/**
 * Creates a new UI.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Ui {

    /**
     * Outputs welcome message.
     *
     * @return Welcome message.
     */
    public static String welcomeMsg() {
        return "Hello! I'm Duke\n  Can you show me da wae?\n";
    }

    /**
     * Outputs success message when task has been added to task list.
     *
     * @param task Message regarding the task.
     * @param taskListSize Size of task list.
     * @return Success and add task detail message.
     */
    public static String addTaskMsg(Task task, int taskListSize) {
        return "Got it. I've added this task:\n    "
                + task + "\n"
                + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.\n";
    }

    /**
     * Outputs success message when task has been removed from task list.
     *
     * @param task Message regarding the task.
     * @param taskListSize Size of task list.
     * @return Success and remove task detail message.
     */
    public static String removeTaskMsg(Task task, int taskListSize) {
        return "Noted. I've removed this task:\n    "
                + task + "\n"
                + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.\n";
    }

    /**
     * Outputs all the tasks stored in task list.
     *
     * @param listOfTasks String of the list of tasks.
     * @return List of tasks message.
     */
    public static String listTasksMsg(String listOfTasks) {
        String listMessage = "Here are the tasks in your list:\n";
        return listMessage + listOfTasks;
    }

    /**
     * Outputs success message when task has been marked.
     *
     * @param task Message regarding the task.
     * @return Success message.
     */
    public static String markTaskMsg(Task task) {
        return "Nice! I've marked this task as done:\n    " + task + "\n";
    }

    /**
     * Outputs success message when task has been unmarked.
     *
     * @param task Message regarding the task.
     * @return Success message.
     */
    public static String unmarkTaskMsg(Task task) {
        return "OK, I've marked this task as not done yet:\n    " + task + "\n";
    }

    /**
     * Outputs success message when task list has been sorted.
     *
     * @return Success message.
     */
    public static String sortedMessage() {
        return "The list has been sorted.";
    }

    /**
     * Handles unknown input.
     *
     * @return Unknown input message.
     */
    public static String unknownInputMsg() {
        return "Dat is not da wae\n";
    }

    /**
     * Outputs error message.
     *
     * @param message Error message.
     * @return Error message.
     */
    public static String errorMsg(String message) {
        return message;
    }
}

