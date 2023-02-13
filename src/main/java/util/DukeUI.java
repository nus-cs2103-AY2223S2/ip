package util;

import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * Contains library of String outputs for different cases.
 */
public class DukeUI {
    /**
     * UI for welcoming user.
     * <p>
     * @return message
     */
    public static String welcomeMessage() {
        String s = "Woof! I'm RubiRyo the Japanese Spitz!\n"
                + "Give me a command or type menu to see the commands I know!";
        return s;
    }

    public static String terminateMessage() {
        return "ByeBye! Come play with me again!";
    }

    /**
     * UI for tasks that match keyword provided by user.
     * <p>
     * @return message
     */
    public static String foundTaskMessage() {
        String str = "Here are matching tasks in your list: ";
        return str + System.lineSeparator();
    }

    /**
     * UI for completed tasks.
     * <p>
     * @return message
     */
    public static String markTaskMessage() {
        String str = "Great job! I have checked this task off the list:";
        return str + System.lineSeparator();
    }

    /**
     * UI for incomplete tasks.
     * <p>
     * @return message
     */
    public static String unmarkTaskMessage() {
        String str = "No worries! I have unchecked this task in the list: ";
        return str + System.lineSeparator();
    }

    /**
     * UI for number of tasks in the list.
     * <p>
     * @param numOfTasks
     * @return number of tasks message
     */
    public static String updateTaskMessage(int numOfTasks) {
        String sizeMessage = String.format("There are currently %d task(s) in the list!", numOfTasks);
        return sizeMessage;
    }

    /**
     * UI for deleting task in the list.
     * <p>
     * @param numOfTasks
     * @return message
     */
    public static String deleteTaskMessage(int numOfTasks) {
        String okMessage = "Okay! I have removed the task!";
        return okMessage + System.lineSeparator() + updateTaskMessage(numOfTasks);
    }

    /**
     * UI for adding event task to the list.
     * <p>
     * @param numOfTasks
     * @return message
     */
    public static String eventTaskMessage(Event event, int numOfTasks) {
        String okMessage = String.format("I have added: %s !", event);
        return okMessage + System.lineSeparator() + updateTaskMessage(numOfTasks);
    }

    /**
     * UI for adding deadline task to the list.
     * <p>
     * @param numOfTasks
     * @return message
     */
    public static String deadlineTaskMessage(Deadline deadline, int numOfTasks) {
        String okMessage = String.format("I have added: %s !", deadline);
        return okMessage + System.lineSeparator() + updateTaskMessage(numOfTasks);
    }

    /**
     * UI for todo task to the list.
     * <p>
     * @param numOfTasks
     * @return message
     */
    public static String todoTaskMessage(ToDo todo, int numOfTasks) {
        String okMessage = String.format("I have added: %s !", todo);
        return okMessage + System.lineSeparator() + updateTaskMessage(numOfTasks);
    }

    public static String indexErrorMessage() {
        return "Item does not exist in list! Please check your list again.";
    }

    public static String eventFormatErrorMessage() {
        return "Please add a description, date and time e.g. party /from 12/2/23 1800 /to 12/2/23 2200";
    }

    public static String deadlineFormatErrorMessage() {
        return "Please add a description, date and time e.g. homework /by 12/12/12 2359";
    }

    public static String missingTaskErrorMessage() {
        return "Your list does not contain this task!";
    }

    /**
     * For when user does not include index of task for mark
     * and unmark commands or does not include description for
     * task commands.
     * <p>
     * @return error message
     */
    public static String incompleteInputErrorMessage() {
        return "Please enter an index or description with the command!";
    }

    /**
     * For when user inputs a command that does not exist.
     * <p>
     * @return error message
     */
    public static String invalidInputErrorMessage() {
        return "Command not recognised, please enter a valid command!";
    }

    public static String emptyInputErrorMessage() {
        return "No command given, please give me one!";
    }

}
