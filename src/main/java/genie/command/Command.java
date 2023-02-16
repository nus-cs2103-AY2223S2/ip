package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;

import java.io.IOException;

/**
 * Deals with execution of commands
 */
public abstract class Command {
    private String commandType;

    /**
     * Executes the required actions for the command and generates its corresponding response.
     * @param taskList
     * @param ui
     * @param storage
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {}

    /**
     * Checks if command is 'bye'
     * @return true if is 'bye', false otherwise
     */
    public boolean isExitCommand() {
        return false;
    }
}
