package peppa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import peppa.commands.DeadlineCommand;
import peppa.commands.DeleteCommand;
import peppa.commands.EventCommand;
import peppa.commands.ExitCommand;
import peppa.commands.FilesCommand;
import peppa.commands.FindCommand;
import peppa.commands.ListCommand;
import peppa.commands.MarkCommand;
import peppa.commands.SelectCommand;
import peppa.commands.TodoCommand;
import peppa.commands.UnmarkCommand;

/**
 * Represents a user interface screen for reading in user inputs and displaying messages in terminal.
 */
public class Ui {
    public static final String DIVIDER = "=============================================";

    /**
     * Constructs a user interface for the command-line version of Peppa.
     */
    public Ui() {

    }

    /**
     * Returns custom message upon a successful add task operation.
     *
     * @param task Task that was added.
     * @return Add task message.
     */
    public static String getAddTaskMessage(Task task) {
        return "Oink! I've added the following task:\n" + "> " + task.toString() + "\n";
    }

    /**
     * Returns a list of commands that the chatbot currently supports.
     *
     * @return List of available commands.
     */
    public static String getCommands() {
        return "> " + ListCommand.COMMAND_WORD + "\n"
                + "> " + FilesCommand.COMMAND_WORD + "\n"
                + "> " + SelectCommand.COMMAND_WORD + "\n"
                + "> " + MarkCommand.COMMAND_WORD + "\n"
                + "> " + UnmarkCommand.COMMAND_WORD + "\n"
                + "> " + FindCommand.COMMAND_WORD + "\n"
                + "> " + TodoCommand.COMMAND_WORD + "\n"
                + "> " + DeadlineCommand.COMMAND_WORD + "\n"
                + "> " + EventCommand.COMMAND_WORD + "\n"
                + "> " + DeleteCommand.COMMAND_WORD + "\n"
                + "> " + ExitCommand.COMMAND_WORD + "\n";
    }

    /**
     * Returns a list of possible data sources.
     *
     * @param sources ArrayList of data sources.
     * @return List of possible data sources.
     */

    public static String getDataSources(ArrayList<File> sources) {
        StringBuilder response = new StringBuilder("Oink! Peppa found "
                + sources.size() + " data sources: \n");
        for (int i = 0; i < sources.size(); i++) {
            response.append((i + 1) + ". " + sources.get(i).getName() + "\n");
        }
        return response + "\n";
    }

    /**
     * Returns custom message upon a successful delete task operation.
     *
     * @param task Task that was deleted.
     * @return Delete task message.
     */
    public static String getDeleteTaskMessage(Task task) {
        return "Oink! I've removed the following task:\n" + "> " + task.toString() + "\n";
    }

    /**
     * Prints any user-provided message.
     *
     * @param message Message to display.
     */

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns custom message for file selection.
     *
     * @return Load file message.
     */
    public static String getLoadFileMessage() {
        return "Please select a file to load data from: \n";
    }

    /**
     * Returns custom message upon a successful mark task as done operation.
     *
     * @param task Task that was marked as done.
     * @return Mark done message.
     */
    public static String getMarkDoneMessage(Task task) {
        return "Oink! I've marked this task as done:\n" + "> " + task.toString() + "\n";
    }

    /**
     * Returns custom message upon execution of a find command.
     *
     * @param map Hashmap that associates a keyword with the list of matching tasks.
     * @return Find task message describing matching tasks.
     */
    public static String getMatchingTasks(HashMap<String, ArrayList<Task>> map) {
        StringBuilder response = new StringBuilder("Oink! ");
        for (String keyword : map.keySet()) {
            ArrayList<Task> matchingTasks = map.get(keyword);
            if (matchingTasks.size() == 0) {
                response.append("No tasks found which match \"" + keyword + "\".\n\n");
                continue;
            }
            response.append("Peppa found " + matchingTasks.size()
                    + " tasks that match \"" + keyword + "\":\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
            }
            response.append("\n");
        }
        return response.toString();
    }

    /**
     * Returns custom message upon a successful select file operation.
     *
     * @param f Name of file that was selected as data source.
     * @return Select file message.
     */

    public static String getSelectFileMessage(File f) {
        return "Oink! Peppa has successfully loaded data from " + f.getName() + ".\n"
                + "How can Peppa assist you today?\n";
    }

    /**
     * Returns the current tasklist, including the details of each task (e.g. id and description).
     *
     * @param tasks List of tasks.
     * @return Tasklist.
     */
    public static String getTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Oink! Here are the tasks in your list currently:\n");
        for (int i = 0; i < tasks.getLength(); i++) {
            response.append((i + 1) + ". " + tasks.retrieveTask(i) + "\n");
        }
        return response.toString();
    }

    /**
     * Returns the number of tasks in the list currently.
     *
     * @param tasks List of tasks.
     * @return Tasklist summary message describing number of tasks in the list.
     */
    public static String getTaskSummary(TaskList tasks) {
        return "You now have " + tasks.getLength() + " tasks in the list.";
    }

    /**
     * Returns custom message upon a successful unmark task as done operation.
     *
     * @param task Task that was unmarked as done.
     * @return Unmark done message.
     */
    public static String getUnmarkDoneMessage(Task task) {
        return "Oink! I've marked this task as undone:\n" + "> " + task.toString() + "\n";
    }

    /**
     * Returns farewell message upon leaving the chatbot.
     *
     * @return Farewell message.
     */
    public static String terminateSession() {
        return "Oink oink! See you again :)";
    }
}
