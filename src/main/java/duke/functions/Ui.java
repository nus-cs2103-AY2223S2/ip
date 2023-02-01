package duke.functions;

import duke.ToDoList;

import duke.exceptions.IndexDukeException;
import duke.tasks.Task;

/**
 * A class that contains different static methods to print messages for user interfaces.
 */
public class Ui {
    private static final String DIVIDER_START_END
            = "____________________________________________________________\n";
    private static final String DIVIDER_MAIN
            = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
    private static final String DIVIDER_ERROR
            = "!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!\n";

    private Ui() {
    }

    /**
     * Prints the welcome message, the very first message that the user sees when Duke runs.
     */
    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(DIVIDER_START_END
                + "What can the Duke help you with today?\n"
                + DIVIDER_START_END);
    }

    /**
     * Prints the exit message, the last message that the user sees before the Duke program ends.
     */
    public static void exitMessage() {
        System.out.println(DIVIDER_START_END
                + "Goodbye, feel free to call the Duke again whenever you need.\n"
                + DIVIDER_START_END);
    }

    /**
     * Prints a message indicating a Task object has been either added into a ToDoList object or
     * deleted from a ToDoList object.
     *
     * @param list The ToDoList object that the specified operation has occurred on.
     * @param task The Task object that has been added or deleted.
     * @param command The type of operation that was applied on the ToDoList object.
     */
    public static void taskAddDelete(ToDoList list, Task task, String command) {
        System.out.println(DIVIDER_MAIN + "The Duke has " + command + " the following task:\n"
                + " - " + task + "\n"
                + "You now have " + list.getToDoCount() + " task!\n" + DIVIDER_MAIN);
    }

    /**
     * Prints a message indicating a Task object in a ToDoList has been either marked or unmarked.
     *
     * @param list The ToDoList object that the specified operation has occurred on.
     * @param index The position of the Task object on the ToDoList object that
     *              the specified operation is applied on.
     * @param command The type of operation that was applied on the Task object.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= list.getToDoCount()).
     */
    public static void taskMarking(ToDoList list, int index, String command) throws IndexDukeException {
        System.out.println(DIVIDER_MAIN
                + "The Duke has " + command + " the following task:\n"
                + " - " + list.getTask(index) + "\n"
                + DIVIDER_MAIN);
    }

    /**
     * Prints a message that shows the list of Task objects and their state
     * (whether marked or unmarked) in the given ToDoList object.
     *
     * @param message The ToDoList object that the list is to be printed from.
     */
    public static void listMessage(String message) {
        System.out.println(DIVIDER_MAIN
                + "TO DO LIST:\n"
                + message
                + DIVIDER_MAIN);
    }

    /**
     * Prints a message that shows the list of Task objects found to match the given keyword
     *
     * @param message The list of Task objects found to match the given keyword.
     * @param keyword The String that is used to find the list of Task objects.
     */
    public static void findResultMessage(String message, String keyword) {
        System.out.println(DIVIDER_MAIN + "The keyword given is:\n\n\"" + keyword
                + "\"\n\nThe Duke has found the following tasks:\n" + message + DIVIDER_MAIN);
    }

    /**
     * Prints a message that is enclosed in error dividers.
     *
     * @param message The message that is to be enclosed with error dividers.
     */
    public static void errorMessage(String message) {
        System.out.println(DIVIDER_ERROR
                + message + "\n"
                +  DIVIDER_ERROR);
    }
}
