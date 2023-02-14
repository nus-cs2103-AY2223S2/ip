package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;

/**
 * Contains a list of responses by Duke.
 */
public class Ui {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");

    /**
     * Returns a response message with line break between each message.
     *
     * @return String Response message with line break
     */
    public static String addLineBreak(String... inputs) {
        String str = inputs[0];
        for(int i = 1; i < inputs.length; i++) {
            str = str + "\n" + inputs[i];
        }
        return str;
    }

    public static String addDoubleLineBreak(String... inputs) {
        String str = inputs[0];
        for(int i = 1; i < inputs.length; i++) {
            str = str + "\n\n" + inputs[i];
        }
        return str;
    }

    public static String welcomeResponse = "Hello! I'm Duke!";

    public static String instructionResponse = addLineBreak("Here are some useful commands formats:",
            "[mark/unmark/delete] [task number] - Select a Task to Mark, Unmark or Delete",
            "find [keyword] - Find a Task with the provided keyword",
            "todo [name] - Add a Todo task",
            "deadline [name] /by:[time] - Add a Deadline Task (format for time: 14 Oct 2023 23:00)",
            "event [name] /from:[time] /to:[time] - Add an Event Task (format for time: 14 Oct 2023 23:00)");

    public static String successfulLoadResponse = "I've successfully retrieved your past task list!";
    public static String unsuccessfulLoadResponse = "Sorry! I was unable to load your task list from memory!";

    public static String askForTaskResponse = "What can I do for you today?";

    public static String unreadableCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Try re-typing your instruction!";
    public static String incompleteFindCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Maybe you forgot to include which keyword to search for! " + "Try re-typing your instruction!";
    public static String incompleteSelectionCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Maybe you forgot to select a Task. " + "Try re-typing your instruction!";
    public static String invalidSelectionCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Remember to select the Task number. " + "Try re-typing your instruction!";
    public static String incompleteAddTodoCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Try re-typing your instruction! (format: todo [name])";
    public static String incompleteAddDeadlineCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Try re-typing your instruction! (format: deadline [name] /by:[time])";
    public static String incompleteAddEventCommandResponse = "I'm sorry, but I don't understand what that means! " +
            "Try re-typing your instruction! (format: event [name] /from:[time] /to:[time])";
    public static String invalidTimeResponse = "OOPS!!! You have key in an invalid date. " +
            "(format: 14 Oct 2023 23:00)";

    public static String exitResponse = "Bye. Hope to see you again soon!";
    public static String unableToSaveErrorMessage = "Sorry, I encountered some issues while saving your task list! ";


    /**
     * Returns a message indicating to user the new task was successfully added.
     *
     * @param t The new task.
     * @param lst The array of tasks.
     * @return String The message indicating to user the new task was successfully added.
     */
    public static String addTaskResponse(Task t, TaskList lst) {
        return "Got it. I've added this task: \n\n" + t + "\n\nNow you have " + lst.getSize()
                + " tasks in the list.";
    }

    /**
     * Returns a message indicating to user selected task was successfully deleted.
     *
     * @param t The selected task.
     * @param lst The array of tasks.
     * @return String The message indicating to user selected task was successfully deleted.
     */
    public static  String deleteTaskResponse(Task t, TaskList lst) {
        return "Noted. I've removed this task: \n\n" + t + "\n\nNow you have " + lst.getSize() + " "
                + "tasks in the list.";
    }

    /**
     * Returns the list of Tasks.
     *
     * @param tasks The array of tasks.
     * @return String The list of Tasks.
     */
    public static String listTaskResponse(TaskList tasks) {
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
    public static String subListTaskResponse(TaskList tasks) {
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
    public static String markTaskResponse(Task t) {
        return "Nice! I've marked this task as done: \n\n " + t + ".";
    }

    /**
     * Returns a message indicating to user selected task was successfully unmarked.
     *
     * @param t The selected task.
     */
    public static String unmarkTaskResponse(Task t) {
        return "OK, I've marked this task as not done yet \n\n" + t + ".";
    }
}
