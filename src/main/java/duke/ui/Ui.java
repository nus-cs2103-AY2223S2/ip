package duke.ui;
import java.util.ArrayList;
import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Handles interactions with the user.
 */
public class Ui {

    private String DUKE_RESPONSE = "Duke's response: \n";

    /**
     * Duke's help message
     */
    private static String HELP_MESSAGE = "The available commands are: \n" +
            "1) list\n" +
            "2) bye\n" +
            "3) todo ________\n" +
            "4) deadline ______ /by ___________ (Note that the date has to be in YYYY-MM-DD format.\n" +
            "5) event ________ /from _______  /to _________\n" +
            "6) unmark ____\n" +
            "7) mark ______ \n" +
            "8) help\n" +
            "9) find ______ \n";

    public String listTasks(TaskList tasks) {
        System.out.println("These are the current tasks: ");
        String taskString = "";
        for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
            Task task = tasks.getTask(i);
            taskString += task.provideDetails() + "\n";
        }
        return DUKE_RESPONSE + "These are the current tasks: \n" + taskString;
    }

    public String findTasks(ArrayList<Task> matchingTasks) {
        String taskString = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            taskString += task.provideDetails() + "\n";
        }
        return DUKE_RESPONSE + "These are the matching tasks: \n" + taskString;
    }

    /**
     * Greets the user.
     */
    public String greet() {
        return "Hi, my name's Duke, how may I be of assistance today? :)";
    }

    /**
     * Responds to the user.
     *
     * @param message The response message to be delivered to the user.
     */
    public String respond(String message) {
        return DUKE_RESPONSE + message;
    }

    /**
     * Displays a loading error message to the user.
     */
    public String showLoadingError() {
        return "The data from the existing file could not be loaded. A new file has been created.";
    }

    /**
     * Displays a command error message to the user.
     */
    public String showCommandError() {
        return "I'm sorry! Either the command you used was not valid, or it was incorrectly formatted. "
                + "To see what constitutes a valid command or format,"
                + " please type 'help'.";
    }

    /**
     * Displays a help message to the user.
     */
    public String showHelpMessage() {
        return HELP_MESSAGE;

    }
}
