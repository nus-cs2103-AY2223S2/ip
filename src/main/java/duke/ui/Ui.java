package duke.ui;

import duke.task.Task;

/**
 * This class deals with any response printing for the UI
 *
 * @author He Shuimei
 * @version 0
 */
public class Ui {
    public final String ERROR_WRONG_DATE_FORMAT = "Error: Format of the date is wrong, it should be in the form of"
            + "YYYY/mm/dd HHmm";
    public final String ERROR_DELETE_TASK = "Error: Item does not exist";
    public final String ERROR_EMPTY_TODO = "Please input something TO DO????!!";
    public final String ERROR_UNKNOWN_COMMAND = "Error: Unknown command";
    public final String ERROR_EXCEPTION_CAUGHT = "Fatal Error: Encountered exception: ";

    /**
     * Prints successful deletion message
     * @param task task that is deleted
     * @param size size of the task list
     * @return String confirmation response
     */
    public String printDeleteMessage(Task task, int size) {
        String l1 = "SENDING TASK TO THE VOID (DELETING) \n";
        String l2 = "\t" + task + "\n";
        String l3 = "You currently have " + size + " tracked tasks";

        return l1 + l2 + l3;
    }

    /**
     * Prints successful add task
     *
     * @param curr single Task object
     * @param size int size of list
     * @return String confirmation response
     */
    public String printNotification(Task curr, int size) {
        String l1 = "Me add your task to list: \n";
        String l2 = "\t" + curr + "\n";
        String l3 = "You currently have " + size + " tracked tasks";

        return l1 + l2 + l3;
    }

    /**
     * Prints the initial start-up message
     *
     * @return String confirmation response
     */
    public String printWelcomeMessage() {
        String welcome = "Welcome to PUKE, the best bot in existence";
        String prompt = "Input a command";
        return welcome + "\n" + prompt;


    }
}
