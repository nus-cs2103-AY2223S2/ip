package duke.functions;

import duke.ToDoList;

import duke.exceptions.IndexDukeException;
import duke.tasks.Task;

/**
 * A class that contains different static methods to return messages for user interfaces.
 */
public class Reply {

    private Reply() {
    }

    /**
     * Returns the welcome message, the very first message that the user sees when Duke runs.
     *
     * @return A String that is the welcome message.
     */
    public static String getWelcomeMessage() {
        return "What can the Duke help you with today?";
    }

    /**
     * Returns the exit message, the message that the user sees after saying bye to Duke.
     *
     * @return A String that is the exit message.
     */
    public static String getExitMessage() {
        return "The Duke has saved your To Do List!\n" +
                "You may safely exit now, feel free to call the Duke again whenever you need.";
    }

    /**
     * Returns a message indicating a Task object has been either added into a ToDoList object or
     * deleted from a ToDoList object.
     *
     * @param list The ToDoList object that the specified operation has occurred on.
     * @param task The Task object that has been added or deleted.
     * @param command The type of operation that was applied on the ToDoList object.
     * @return A String that specifies the operation carried out on which Task object.
     */
    public static String getAddDeleteMessage(ToDoList list, Task task, String command) {
        assert command.equals("add") || command.equals("delete") : "command should be add or delete only";
        return "The Duke has " + command + " the following task:\n"
                + " - " + task + "\n"
                + "You now have " + list.getToDoCount() + " task!\n";
    }

    /**
     * Returns a message indicating a Task object in a ToDoList has been either marked or unmarked.
     *
     * @param list The ToDoList object that the specified operation has occurred on.
     * @param index The position of the Task object on the ToDoList object that
     *              the specified operation is applied on.
     * @param command The type of operation that was applied on the Task object.
     * @return A String that specifies the operation carried out on which Task object.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= list.getToDoCount()).
     */
    public static String getTaskMarkMessage(
            ToDoList list, int index, String command) throws IndexDukeException {
        assert command.equals("mark") || command.equals("unmark") : "command should be mark or unmark only";
        assert index > 0 : "index should be greater than 0";
        return "The Duke has " + command + " the following task:\n"
                + " - " + list.getTask(index) + "\n";
    }

    /**
     * Returns a message that shows the list of Task objects and their state
     * (whether marked or unmarked) in the given ToDoList object.
     *
     * @param message The ToDoList object that the list is to be printed from.
     * @return A String that shows the list of Task objects and their states.
     */
    public static String getListMessage(String message) {
        return "TO DO LIST:\n" + message;
    }

    /**
     * Returns a message that shows the list of Task objects that is about to happen.
     *
     * @param message The list of Task objects to be printed with the reminder message.
     * @return A String that is a reminder that shows the list of Task objects that is
     *         about to happen.
     */
    public static String getReminderMessage(String message) {
        return "The following tasks are about to happen!!!\n\n" + message + "\n";
    }

    /**
     * Returns a message that shows the list of Task objects found to match the given keyword.
     *
     * @param message The list of Task objects found to match the given keyword.
     * @param keyword The String that is used to find the list of Task objects.
     * @return A String that shows the list of Task objects found to match the given keyword.
     */
    public static String getFindResultMessage(String message, String keyword) {
        return "The keyword given is:\n\n\"" + keyword
                + "\"\n\nThe Duke has found the following tasks:\n" + message;
    }

    /**
     * Returns a message that informs user it is an error.
     *
     * @param message The error message.
     * @return A String that informs user it is an error and its specific error message.
     */
    public static String getErrorMessage(String message) {
        return "The Duke has encountered an error!\n" + message;
    }
}
