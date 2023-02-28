package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the basic Command, from which all other commands inherit from
 */
public class Command {
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
     * @param ui the user interface to interact with the user
     * @param storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if an issue arises
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // do the commands purpose
        // ui.print() the correct output
        return;
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
