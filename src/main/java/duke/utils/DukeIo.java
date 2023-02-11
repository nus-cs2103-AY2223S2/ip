package duke.utils;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Formatter class to format outputs to users' terminal.
 */
public class DukeIo {
    private static final String CMD_LIST = "\n[list] : Show a list of all tasks currently\n"
                                         + "[todo] : Add a to-do task\n"
                                         + "[deadline] : Add a task to be completed by a date\n"
                                         + "[event] : Add an event from start time to end time\n"
                                         + "[mark] : Mark a task as completed\n"
                                         + "[unmark] : Unmark a completed task\n"
                                         + "[delete] : Delete a task from the list\n";
    private static final String HELLO = "Hello! I am Duke.\n"
                                        + "What's on your mind today?\n";
    private static final String SAVE_SUCCESS = "Successfully saved all tasks\n";
    private static final String SAVE_FAILURE = "FAILED TO SAVE ALL TASKS\n";
    private static final String QUIT = "Quitting Duke...\n" + "See you soon!\n";

    /**
     * Displays message on screen, writes each String as a newline to standard output.
     * @param message custom message to echo
     */
    public String echoMessage(String message) {
        return message;
    }

    /**
     * Displays greeting message for Duke.
     */
    public String printHello() {
        return HELLO;
    }

    /**
     * Displays exit message upon quit.
     */
    public String printQuit() {
        return QUIT;
    }


    /**
     * Displays a success toast message upon addition of a Task to TaskList.
     * @param t Task object and its inherited classes.
     */
    public String notifySuccessAdd(Task t) {
        String out = "Successfully added: " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays a success toast message upon updating the state of the task to "Completed".
     * @param t Task obejct and its inherited classes.
     */
    public String notifySuccessComplete(Task t) {
        String out = "Successfully completed: " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays a success toast upon updating the state from "Completed" to "Incomplete".
     * @param t Task object and its inherited classes.
     */
    public String notifyUnmark(Task t) {
        String out = "Unmarked task: " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays an error toast when user tries to mark an already marked Task.
     * @param t Task object and its inherited classes.
     */
    public String notifyMarkFail(Task t) {
        String out = "Cannot mark completed task: " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays error toast when user tries to unmark an already unmarked Task.
     * @param t Task object and its inherited classes.
     */
    public String notifyUnmarkFail(Task t) {
        String out = "Cannot unmark incomplete task: " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays the number of existing tasks in the TaskList.
     */
    public String showCount() {
        StringBuilder str = new StringBuilder();
        String isare;
        String s;
        int taskCount = TaskList.getTaskCount();
        if (taskCount > 1) {
            isare = " are: ";
            s = " tasks";
        } else {
            isare = " is: ";
            s = " task";
        }
        str.append("There" + isare + Integer.toString(taskCount)
                             + s + " in the list.\n");
        return str.toString();
    }

    //CHECKSTYLE.OFF: SingleSpaceSeparator
    /**
     * Displays an indexed list of all existing tasks in the TaskList.
     */
    public String showAll() {
        StringBuilder str = new StringBuilder();
        int taskCount = TaskList.getTaskCount();
        ArrayList<Task> allTasks = new ArrayList<>(TaskList.getAllTasks());
        str.append("All Tasks:\n");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   "  + Integer.toString(i + 1) + ": "
                                 + allTasks.get(i).toString();
            str.append(showString + "\n");
        }
        return str.toString();
    }
    //CHECKSTYLE.ON: SingleSpaceSeparator

    /**
     * Displays an indexed list of filtered tasks.
     * @param filteredTasks
     */
    public String showFiltered(List<Task> filteredTasks) {
        StringBuilder str = new StringBuilder();
        int taskCount = filteredTasks.size();
        str.append("Find results:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   " + Integer.toString(i + 1) + ": "
                                 + filteredTasks.get(i).toString();
            str.append(showString + "\n");
        }
        return str.toString();
    }

    /**
     * Displays error toast when user inputs an Invalid Command.
     * A section of valid commands guide is displayed in a new line.
     */
    public static String showInvalidCommand() {
        return "Invalid Command!\n"
            + CMD_LIST;
    }

    /**
     * Displays error toast upon Exception.
     * @param e Exception thrown
     */
    public String showError(Exception e) {
        return e.getMessage() + "\n";
    }

    /**
     * Displays success toast after loading TaskList from saved .txt file.
     */
    public String notifyLoad() {
        return "Loaded successfully from previous session.\n";
    }

    /**
     * Displays success toast after saving TaskList upon quit.
     */
    public String notifySave() {
        return SAVE_SUCCESS;
    }

    /**
     * Displays failure toast upon save failure
     */
    public String notifySaveFailure() {
        return SAVE_FAILURE;
    }

    /**
     * Displays failure toast upon no find results
     */
    public String notifyZeroHits() {
        return "No tasks found.\n";
    }

    /**
     * Displays user-friendly interpretation of task deleted.
     * @param taskIndex
     * @return Feedback after task has been deleted successfully.
     */
    public String showDeleted(int taskIndex) {
        return TaskList.getAllTasks().get(taskIndex).toString() + " deleted.\n";
    }
}


