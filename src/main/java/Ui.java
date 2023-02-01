package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents Duke's interaction with user.
 */
public class Ui {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private Scanner sc;

    /**
     * Returns a welcome message.
     *
     * @return String The welcome message
     */
    public String welcomeResponse() {
        return "Hello! Nice to meet you! I'm Duke\n";
    }

    /**
     * Returns a message indicating load from memory was successful.
     *
     * @return String The message indicating load from memory was successful.
     */
    public String successfulLoadResponse() {
        return "I've successfully retrieved your past task list!";
    }

    /**
     * Returns a message asking user for a Task.
     *
     * @return String The message asking user for a Task.
     */
    public String askForTaskResponse() {
        return "What can I do for you today?";
    }

    /**
     * Returns the list of Tasks.
     *
     * @param tasks The array of tasks.
     * @return String The list of Tasks.
     */
    public String listTaskResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "You have 0 task to complete at the moment!";
        } else {
            return "Here are the task in your list: \n\n" + tasks.toStringList() + "\n\nYou have " + tasks.getSize()
                    + " tasks in the list.";
        }
    }

    /**
     * Returns the sublist of Tasks.
     *
     * @param tasks The array of tasks.
     * @return String The sublist of Tasks.
     */
    public String subListTaskResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "You have 0 matching task!";
        } else {
            return "Here are the matching tasks in your list: \n\n" + tasks.toStringList() + "\n\nYou have "
                    + tasks.getSize() + " matching tasks in the list.";
        }
    }

    /**
     * Returns a message indicating to user selected task was successfully marked.
     *
     * @param t The selected task.
     * @return String The message indicating to user selected task was successfully marked.
     */
    public String markTaskResponse(Task t) {
        return "Nice! I've marked this task as done: \n\n " + t;
    }

    /**
     * Returns a message indicating to user selected task was successfully unmarked.
     *
     * @param t The selected task.
     */
    public String unmarkTaskResponse(Task t) {
        return "OK, I've marked this task as not done yet \n\n" + t;
    }

    /**
     * Returns a message indicating to user selected task was successfully deleted.
     *
     * @param t The selected task.
     * @param lst The array of tasks.
     * @return String The message indicating to user selected task was successfully deleted.
     */
    public String deleteTaskResponse(Task t, TaskList lst) {
        return "Noted. I've removed this task: \n\n" + t + "\n\nNow you have " + lst.getSize() + " "
                + "tasks in the list.";
    }

    /**
     * Returns a message indicating to user the new task was successfully added.
     *
     * @param t The new task.
     * @param lst The array of tasks.
     * @return String The message indicating to user the new task was successfully added.
     */
    public String addTaskResponse(Task t, TaskList lst) {
        return "Got it. I've added this task: \n\n" + t + "\n\nNow you have " + lst.getSize()
                + " tasks in the list.";
    }

    /**
     * Returns a message indicating to user that Duke has exited program.
     *
     * @return String The message indicating to user that Duke has exited program.
     */
    public String exitResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message indicating to user that a task has not been selected.
     *
     * @return String The message indicating to user that a task has not been selected.
     */
    public String taskNotChosenErrorMessage() {
        return "OPPS! You have not selected a task.";
    }

    /**
     * Returns a message indicating to user that list of tasks could not be saved to file. This is likely due to an
     * incorrect file path.
     *
     * @return String The message indicating to user that list of tasks could not be saved to file.
     */
    public String unableToSaveErrorMessage() {
        return "Sorry, memory cannot be saved!";
    }

    /**
     * Returns a message indicating to user that commandline input is invalid.
     *
     * @return String The message indicating to user that commandline input is invalid.
     */
    public String unreadableCommandErrorMessage() {
        return "I'm sorry, but I don't understand what that means! Try re-typing your instruction!";
    }

    /**
     * Returns a message indicating to user that list of tasks could not be loaded from file. This is likely due to an
     * incorrect file path.
     *
     * @return String The message indicating to user that list of tasks could not be loaded from file.
     */
    public String loadingErrorMessage() {
        return "Sorry! I was unable to load your task list from memory!";
    }

    /**
     * Returns a message indicating to user that commandline input is incomplete.
     *
     * @return String The message indicating to user that commandline input is incomplete.
     */
    public String incompleteCommandErrorMessage() {
        return "OOPS!!! The description of this task is incomplete.";
    }

    /**
     * Returns a message indicating to user that date entered as commandline input is not in correct format.
     *
     * @return String The message indicating to user that date entered as commandline input is not in correct format.
     */
    public String invalidTiming() {
        return "OOPS!!! You have key in an invalid date. (Format Example: 14 Oct 2023 23:00";
    }

}
