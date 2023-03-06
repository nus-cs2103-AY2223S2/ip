package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the basic Command, from which all other commands inherit from
 */
public class Command {
    protected static final String INVALID_NUMBER_MESSAGE = "Please enter a valid number";
    protected static final String EMPTY_LIST_MESSAGE = "You do not have any items in your list!";
    protected final String command;

    /**
     * Returns a Command with empty String command
     */
    public Command() {
        this("");
    }

    /**
     * Returns a Command with the command stored
     *
     * @param command String of the command to be stored
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Executes the task of the command on TaskList
     * Display the output via Ui
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if an issue arises
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // do the commands purpose for command line
        return;
    }

    /**
     * Returns the String of the output of the execution
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @return String empty string
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        // do the commands purpose and returns as String for GUi
        return "";
    }
    protected String listString(TaskList tasks) throws DukeException {
        String lst = String.format("1. %s", tasks.get(1));
        for (int i = 2; i <= tasks.size(); i++) {
            lst += String.format("\n%d. %s", i, tasks.get(i));
        }
        return lst;
    }
    /**
     * Returns a boolean: true if the command is an ExitCommand, false otherwise
     *
     * @return boolean if the command is an ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
