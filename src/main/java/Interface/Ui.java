package duke;

import duke.task.Task;

/**
 * Contains a list of responses by Duke.
 */
public class Ui {
    protected static String welcomeResponse = "Hi! Duke at your service!";

    protected static String instructionResponse = addLineBreak("Here are some useful command formats:",
            "[mark/unmark/delete] [task number] - Select a Task to Mark, Unmark or Delete",
            "find [keyword] - Find a Task with the provided keyword",
            "todo [name] - Add a Todo task",
            "deadline [name] /by:[time] - Add a Deadline Task (format for time: 14 Oct 2023 23:00)",
            "event [name] /from:[time] /to:[time] - Add an Event Task (format for time: 14 Oct 2023 23:00)");

    protected static String successfulLoadResponse = "I've successfully retrieved from memory!";
    protected static String unsuccessfulLoadResponse = "Sorry! I was unable to retrieve from memory!";

    protected static String askForTaskResponse = "What can I do for you today?";

    protected static String unreadableCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Try re-typing your instruction!";
    protected static String incompleteFindCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Maybe you forgot to include which keyword to search for! " + "Try re-typing your instruction!";
    protected static String incompleteSelectionCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Maybe you forgot to select a Task. " + "Try re-typing your instruction!";
    protected static String invalidSelectionCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Remember to select the Task number. " + "Try re-typing your instruction!";
    protected static String incompleteUpdateCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Maybe you forgot to include what to update. " + "Try re-typing your instruction! "
            + "(format: update [num] /[item]:[new information])";
    protected static String incompleteAddTodoCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Try re-typing your instruction! (format: todo [name])";
    protected static String incompleteAddDeadlineCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Try re-typing your instruction! (format: deadline [name] /by:[time])";
    protected static String incompleteAddEventCommandResponse = "I'm sorry, but I don't understand what that means! "
            + "Try re-typing your instruction! (format: event [name] /from:[time] /to:[time])";
    protected static String invalidTimeResponse = "OOPS!!! You have key in an invalid date. "
            + "(format: 14 Oct 2023 23:00)";

    protected static String unsuccessfulSaveResponse = "Sorry! I was not able to save your previous instructions.";

    /**
     * Returns a Response to signify end of program.
     *
     * @param isSaved The boolean showing whether data was successfully saved.
     * @return String The String response to signify end of program.
     */
    public static String endResponse(boolean isSaved) {
        if (isSaved) {
            return "Bye. Hope to see you again soon!";
        } else {
            return "I'm sorry, I encountered some issues while saving your task list! Bye. Hope to see you again soon!";
        }
    }
    /**
     * Returns a response message with line break between each message.
     *
     * @return String Response message with line break
     */
    public static String addLineBreak(String... inputs) {
        String str = "";
        for (int i = 0; i < inputs.length; i++) {
            if (str.equals("")) {
                str = inputs[i];
            } else if (inputs[i].equals("")) {
                str = str;
            } else {
                str = str + "\n" + inputs[i];
            }
        }
        return str;
    }

    /**
     * Returns a response message with double line break between each message.
     *
     * @return String Response message with line break
     */
    public static String addDoubleLineBreak(String... inputs) {
        String str = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            str = str + "\n\n" + inputs[i];
        }
        return str;
    }

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
    public static String deleteTaskResponse(Task t, TaskList lst) {
        return "Noted. I've removed this task: \n\n" + t + "\n\nNow you have " + lst.getSize() + " "
                + "tasks in the list.";
    }

    /**
     * Returns a message indicating to user that the item to update is not available.
     *
     * @param t The selected task to update.
     * @return String The message indicating to user selected task cannot be updated.
     */
    public static String invalidItemUpdateResponse(Task t) {
        return "I'm sorry, but this task below cannot be updated: \n\n" + t + "\n\nMaybe the item to be updated is "
                + "invalid. Try re-typing your instruction! (format: update [num] /[item]:[new information])";
    }

    /**
     * Returns a message indicating to user selected task was successfully marked.
     *
     * @param t The selected task.
     * @return String The message indicating to user selected task was successfully marked.
     */
    public static String updateTaskResponse(Task t) {
        return "Nice! I've update this task: \n\n" + t;
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
        return "Nice! I've marked this task as done: \n\n" + t;
    }

    /**
     * Returns a message indicating to user selected task was successfully unmarked.
     *
     * @param t The selected task.
     * @return String The message indicating to user selected task was successfully unmarked.
     */
    public static String unmarkTaskResponse(Task t) {
        return "OK, I've marked this task as not done yet \n\n" + t;
    }

    /**
     * Returns a message indicating to user that the task number selected by user is invalid.
     *
     * @param num The number of the task input by user.
     * @return String The message indicating to user selected task was invalid.
     */
    public static String outOfBoundSelectionResponse(int num) {
        return "I'm sorry, but I don't understand what that means! "
                + String.format("Remember to select the Task number (from 1 to %d). ", num)
                + "Try re-typing your instruction!";
    }
}
