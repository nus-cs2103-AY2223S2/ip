package duke.utils;

import java.util.ArrayList;
import java.util.List;

import duke.Tasks.Task;
import duke.Tasks.TaskList;

/**
 * Formatter class to format outputs to users' terminal.
 */
public class DukeIo {

    private static final String INPUT_PROMPT = "MyDuke >    "; 

    private static final String LOGO =  " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    
    private static final String SEPERATOR = "======================================================\n";

    private static final String CMD_LIST = SEPERATOR
                                         + "[list] : Show a list of all tasks currently\n"
                                         + "[todo] : Add a to-do task\n"
                                         + "[deadline] : Add a task to be completed by a date\n"
                                         + "[event] : Add an event from start time to end time\n"
                                         + "[mark] : Mark a task as completed\n"
                                         + "[unmark] : Unmark a completed task\n"
                                         + "[delete] : Delete a task from the list\n"
                                         + SEPERATOR;

    private static final String REPLY = "|    ";
    
    
    private static final String HELLO = REPLY + "Hello from\n" 
                                        + SEPERATOR
                                        + LOGO
                                        + SEPERATOR
                                        + REPLY + "What's on your mind today?\n"
                                        + SEPERATOR;

    private static final String SAVE_SUCCESS = "Successfully saved all tasks\n";

    private static final String SAVE_FAILURE = "FAILED TO SAVE ALL TASKS\n";
    
    private static final String QUIT = SEPERATOR 
                                    + REPLY + "Quitting Duke...\n"
                                    + REPLY + "See you soon!\n"
                                    + SEPERATOR;
    
    /**
     * Displays message on screen, writes each String as a newline to standard output.
     * @param String custom message to echo
     */
    public void echoMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays greeting message for Duke.
     */
    public void printHello() {
        System.out.print(HELLO);
    }

    /**
     * Displays exit message upon quit.
     */
    public void printQuit() {
        System.out.print(QUIT);
    }

    /**
     * Displays an indicator to promptuser inputs.
     */
    public void showPrompt() {
        System.out.print(INPUT_PROMPT);
    }

    /**
     * Displays a success toast message upon addition of a Task to TaskList.
     * 
     * @param t Task object and its inherited classes.
     */
    public void notifySuccessAdd(Task t) {
        System.out.println(SEPERATOR
                + REPLY + "Successfully added: " + t.toString());
    }

    /**
     * Displays a success toast message upon updating the state of the task to "Completed".
     * 
     * @param t Task obejct and its inherited classes.
     */
    public void notifySuccessComplete(Task t) {
        System.out.println(SEPERATOR
                + REPLY + "Successfully completed: " + t.toString());
    }

    /**
     * Displays a success toast upon updating the state from "Completed" to "Incomplete".
     *  
     * @param t Task object and its inherited classes.
     */
    public void notifyUnmark(Task t) {
        System.out.println(SEPERATOR
                + REPLY + "Unmarked task: " + t.toString());
    }

    /**
     * Displays an error toast when user tries to mark an already marked Task.
     * 
     * @param t Task object and its inherited classes.
     */
    public void nofifyMarkFail(Task t) {
        System.out.println(SEPERATOR
            + REPLY + "Cannot mark completed task: " + t.toString());
    }

    /**
     * Displays error toast when user tries to unmark an already unmarked Task.
     * 
     * @param t Task object and its inherited classes.
     */
    public void notifyUnmarkFail(Task t) {
        System.out.println(SEPERATOR 
            + REPLY + "Cannot unmark incomplete task: " + t.toString());
    }

    /**
     * Displays the number of existing tasks in the TaskList.
     */
    public void showCount() {
        String isare;
        String s;
        int taskCount = TaskList.taskCount;
        if (taskCount > 1) {
            isare = " are: ";
            s = " tasks";
        } else {
            isare = " is: ";
            s = " task";
        }
        System.out.println(REPLY + "There" + isare + Integer.toString(taskCount) 
                            + s + " in the list.\n" + SEPERATOR);
    }

    /**
     * Displays an indexed list of all existing tasks in the TaskList.
     */
    public void showAll() {
        int taskCount = TaskList.taskCount;
        ArrayList<Task> allTasks = new ArrayList<>(TaskList.allTasks);
        System.out.println(SEPERATOR + "All Tasks:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   "  + Integer.toString(i+1)+ ": "
                                + allTasks.get(i).toString();
            System.out.println(showString);     
        }
        System.out.println(SEPERATOR);
    }

    /**
     * Displays an indexe list of filtered tasks.
     * 
     * @param filteredTasks
     */
    public void showFiltered(List<Task> filteredTasks) {
        int taskCount = filteredTasks.size();
        System.out.println(SEPERATOR + "Find results:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   " + Integer.toString(i+1)+ ": "
                                + filteredTasks.get(i).toString();
            System.out.println(showString);
        }
        System.out.println(SEPERATOR);
    }

    /**
     * Displays error toast when user inputs an Invalid Command.
     * A section of valid commands guide is displayed in a new line.  
     */
    public void showInvalidCommand() {
        System.out.println(SEPERATOR
                        + REPLY + "Invalid Command!\n"
                        + CMD_LIST);
    }

    /**
     * Displays error toast upon Exception.
     * @param e
     */
    public void showError(Exception e) {
        System.out.println(SEPERATOR + e.getMessage() + "\n" + SEPERATOR);
    }

    /**
     * Displays success toast after loading TaskList from saved .txt file.
     */
    public void notifyLoad() {
        System.out.println("Loaded successfully from previous session.");
    }

    /**
     * Displays success toast after saving TaskList upon quit.
     */
    public void notifySave() {
        System.out.println(SAVE_SUCCESS);
    }
    
    /**
     * Displays failure toast upon save failure
     */
    public void notifySaveFailure() {
        System.out.println(SAVE_FAILURE);
    }

    /**
     * Displays failure toast upon no find results
     */
    public void notifyZeroHits() {
        System.out.println(SEPERATOR + "No tasks found." + SEPERATOR);
    }
}
