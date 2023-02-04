package colette.gui;

import colette.TaskList;
import colette.command.CommandType;
import colette.exception.ColetteException;
import colette.exception.IndexErrorType;
import colette.task.Task;
import colette.task.TaskType;

/** Class that handles the text of the graphical user interface */
public class GuiText {

    /**
     * Generates greeting text.
     *
     * @return Greeting text.
     */
    public static String showGreeting() {
        String greeting = "Hello! I'm Colette.\n"
                + "What can I do for you?";
        return greeting;
    }

    /**
     * Generates error text from given exception.
     *
     * @param e Error.
     * @return Error text.
     */
    public String showErrorMessage(ColetteException e) {
        return e.getMessage();
    }

    /**
     * Generates the error message to do
     * with failing to parse a date.
     *
     * @return Error message.
     */
    public static String generateDateErrorMessage() {
        return "I can't read these dates. I only know dates in the format \'YYYY-MM-DD\'.";
    }

    /**
     * Generates the error message to do
     * with provided task list index being
     * invalid.
     *
     * @param indexErrorType What is invalid about provided task list index.
     * @return Error message.
     */
    public static String generateIndexErrorMessage(IndexErrorType indexErrorType) {
        switch (indexErrorType) {
        case NO_NUMBER:
            return "You didn't provide a task number. Unless it's invisible?!";
        case OUT_OF_BOUNDS:
            return "There aren't that many tasks in the list."
                    + " Would you like to add more...?";
        case NOT_A_NUMBER:
            return "That's not a number! At least, I don't think it is...";
        case NEGATIVE:
            return "I can only count in positive numbers...";
        case TOO_MANY_NUMBERS:
            return "That's too many numbers! I can only handle one...";
        default:
            return null;
        }
    }

    /**
     * Generates the error message to do with
     * not providing enough arguments for
     * the creation of a task.
     *
     * @param taskType Type of task that failed to be created.
     * @return Error message.
     */
    public static String generateMissingArgumentErrorMessage(TaskType taskType) {
        String suggestedName = "For the description, what about something like pet every dog? "
                + "That's something I want to do.";
        switch (taskType) {
        case TODO:
            return "The description's missing!\n"
                    + "\n"
                    + "I accept TODOs in the format:\n"
                    + "todo [description]\n"
                    + "\n"
                    + suggestedName;
        case EVENT:
            return "Something's missing here! Either the description, the start date or the end date.\n"
                    + "\n"
                    + "I accept EVENTs in the format:\n"
                    + "event [description] /from [start time] /to [end time]\n"
                    + "\n"
                    + suggestedName;
        case DEADLINE:
            return "Something's missing here. Either the description or the date.\n"
                    + "\n"
                    + "I accept DEADLINEs in the format:\n"
                    + "deadline [description] /by [due date]\n"
                    + "\n"
                    + suggestedName;
        default:
            return null;
        }
    }

    /**
     * Generates the error message to do with
     * receiving an unknown command.
     *
     * @return Error message.
     */
    public static String generateUnknownCommandErrorMessage() {
        return "I don't know what that means. I must have forgotten.\n"
                + "\n"
                + "You can see what I remember using \'help\'.";
    }

    /**
     * Generates the error message to do with
     * not receiving a keyword when finding
     * tasks.
     *
     * @return Error message.
     */
    public static String generateMissingKeywordErrorMessage() {
        return "I'm lost. You need to give me at least one keyword to look for!";
    }

    /**
     * Generates the generic error message for
     * when something goes wrong.
     *
     * @return Error message.
     */
    public static String generateGenericErrorMessage() {
        return "Something's gone wrong!";
    }

    /**
     * Generates text response listing user's tasks.
     *
     * @param tasks User's task list.
     * @return Text response listing tasks.
     */
    public String showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There are currently no tasks in your list. Would you like to add some?";
        } else {
            return "Here are all the tasks in your list:\n"
                + tasks;
        }
    }

    /**
     * Generates text response for adding task to task list.
     *
     * @param task Task to be added.
     * @param tasks Task list.
     * @return Text response for adding task to task list.
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Got it! I've added this task:\n"
                + task + "\n"
                + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Generates text response for marking task.
     *
     * @param task Task that has been marked.
     * @return Text response for marking task.
     */
    public String showMarkTask(Task task) {
        return "Yay! I've marked this task as done:\n" + task;
    }

    /**
     * Generates text response for unmarking task.
     *
     * @param task Task that has been unmarked.
     * @return Text response for unmarking task.
     */
    public String showUnmarkTask(Task task) {
        return "Okay. I've marked this task as not done:\n" + task;
    }

    /**
     * Generates text response for deleting task.
     *
     * @param task Deleted task.
     * @param tasks Task list.
     * @return Text response for deleting task.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Alright. I've removed this task:\n"
                + task + "\n"
                + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Generates goodbye text.
     *
     * @return Goodbye text.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates text listing matching tasks.
     *
     * @param tasks TaskList containing matching tasks.
     * @return Text listing matching tasks.
     */
    public String showFind(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks matching any of your keywords.";
        } else {
            return "Here are all the matching tasks in your list:\n"
                    + tasks;
        }
    }

    /**
     * Generates text showing what happened
     * when loading from storage.
     *
     * @param isSuccessful Whether loading from storage succeeded.
     * @return Text showing what happened when loading from storage.
     */
    public String showLoadFromStorageStatus(boolean isSuccessful) {
        return isSuccessful ? "Tasks successfully loaded from storage!" : "Loading from storage failed.";
    }

    /**
     * Generates the text to put in the separate
     * help window.
     *
     * @return Text to put in the separate help window.
     */
    public static String generateHelpWindowText() {
        String acceptedCommands = "Here are all the commands that I accept!\n"
                + "\n";
        return acceptedCommands + CommandType.getAllCommandFormatString();
    }

    /**
     * Returns the text to show when
     * user uses the help command.
     *
     * @param isHelpWindowOpen Whether the help window is already open.
     * @return Text to show.
     */
    public static String showHelp(boolean isHelpWindowOpen) {
        return !isHelpWindowOpen ? "The help window has been opened!" : "The help window is already open!";
    }

}
