package commands;

import java.io.IOException;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;



/**
 * Represents a command and the execution action
 */
public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    /**
     * Returns boolean value that representing a command of exit or not
     * @return false for all instance of Commands except for ExitCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * A getter function to get the command
     * @return the String representation of the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Implements the execution action of the command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return;
    }

    public void replace(TaskList tasks, Ui ui, Storage storage, int idx) {
        return;
    }

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        return "ttt";
    }
}
