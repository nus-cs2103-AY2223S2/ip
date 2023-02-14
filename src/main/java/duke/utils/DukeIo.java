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
                                         + "[delete] : Delete a task from the list\n"
                                         + "[find] : Find a task containing the keyword\n"
                                         + "[sort] : Sort tasks alphabetically or by date\n";
    private static final String HELLO = "Hello! I am Duke.\n"
                                        + "What's on your mind today?\n";
    private static final String SAVE_SUCCESS = "Successfully saved all tasks\n";
    private static final String SAVE_FAILURE = "FAILED TO SAVE ALL TASKS\n";
    private static final String QUIT = "Quitting Duke...\n" + "See you soon!\n";

    /**
     * Displays message on screen, writes each String as a newline to standard output.
     * @param message custom message to echo
     * @return User-friendly interpretation of message echoed to GUI.
     */
    public String echoMessage(String message) {
        return message;
    }

    /**
     * Displays greeting message for Duke.
     * @return User-friendly interpretation of welcome message on GUI.
     */
    public String printHello() {
        return HELLO;
    }

    /**
     * Displays exit message upon quit.
     * @return User-friendly quit message rendered on GUI.
     */
    public String printQuit() {
        return QUIT;
    }


    /**
     * Displays a success toast message upon addition of a Task to TaskList.
     * @param t Task object and its inherited classes.
     * @return User-friendly success toast and task description rendered on GUI.
     */
    public String notifySuccessAdd(Task t) {
        String out = "Successfully added:\n" + "    " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays a success toast message upon updating the state of the task to "Completed".
     * @param t Task obejct and its inherited classes.
     * @return User-friendly success toast and completed task rendered on GUI.
     */
    public String notifySuccessComplete(Task t) {
        String out = "Successfully completed:\n" + "    " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays a success toast upon updating the state from "Completed" to "Incomplete".
     * @param t Task object and its inherited classes.
     * @return User-friendly success toast of unmarked task rendered on GUI.
     */
    public String notifyUnmark(Task t) {
        String out = "Unmarked task:\n" + "    " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays an error toast when user tries to mark an already marked Task.
     * @param t Task object and its inherited classes.
     * @return User-friendly failure toast rendered on GUI.
     */
    public String notifyMarkFail(Task t) {
        String out = "Cannot mark completed task:\n" + "    " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays error toast when user tries to unmark an already unmarked Task.
     * @param t Task object and its inherited classes.
     * @return User-friendly failure toast rendered on GUI.
     */
    public String notifyUnmarkFail(Task t) {
        String out = "Cannot unmark incomplete task:\n" + "    " + t.toString() + "\n";
        return out;
    }

    /**
     * Displays the number of existing tasks in the TaskList.
     * @return User-friendly display about number of tasks in task list rendered on GUI.
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
     * Displays an indexed list of all existing tasks in the TaskList
     * @return User-friendly interpretation of indexed list of all tasks rendered on GUI.
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
     * @param filteredTasks List of Tasks containing filtered tasks.
     * @return User-friendly list of filtered tasks rendered on GUI.
     */
    public String showFiltered(List<Task> filteredTasks) {
        StringBuilder str = new StringBuilder();
        int taskCount = filteredTasks.size();
        str.append("Find results:\n");
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
     * @return User-friendly help message rendered on GUI upon InvalidCommand.
     */
    public static String showInvalidCommand() {
        return "Invalid Command!\n"
            + CMD_LIST;
    }

    /**
     * Displays error toast upon Exception.
     * @param e Exception thrown
     * @return User-friendly feedback of recoverable exception rendered on GUI.
     */
    public String showError(Exception e) {
        return e.getMessage() + "\n";
    }

    /**
     * Displays success toast after loading TaskList from saved .txt file.
     * @return User-friendly update upon successful load.
     */
    public String notifyLoad() {
        return "Loaded successfully from previous session.\n";
    }

    /**
     * Displays success toast after saving TaskList upon quit.
     * @return User-friendly success toast after save upon quit.
     */
    public String notifySave() {
        return SAVE_SUCCESS;
    }

    /**
     * Displays failure toast upon save failure
     * @return User-friendly failure toast after quit.
     */
    public String notifySaveFailure() {
        return SAVE_FAILURE;
    }

    /**
     * Displays failure toast upon no find results
     * @return User-friendly feedback when there are no tasks matching search keyword.
     */
    public String notifyZeroHits() {
        return "No tasks found.\n";
    }

    /**
     * Displays user-friendly interpretation of task deleted.
     * @param taskIndex Index of task to be deleted from TaskList.
     * @return Feedback after task has been deleted successfully.
     */
    public String showDeleted(int taskIndex) {
        return TaskList.getAllTasks().get(taskIndex).toString() + " deleted.\n";
    }
}


