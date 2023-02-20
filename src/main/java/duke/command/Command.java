package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    private final String fullCommand;

    /**
     * Returns a Command object.
     * @param command full unparsed command.
     */
    public Command(String command) {
        fullCommand = command;
    }

    /**
     * Returns full unparsed command.
     * @return command.
     */
    public String getFullCommand() {
        return fullCommand;
    }

    /**
     * Executes the command.
     *
     * @param taskList contains the task list.
     * @param ui       deals with interactions with the user.
     * @param storage  deals with loading and saving tasks from file.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns the inverse of the command for undo. By default, returns itself if command has no inverse.
     * @param taskList contains the task list.
     * @return new command that will undo the previous command.
     */
    public Command inverseCommand(TaskList taskList) {
        return this;
    };

    /**
     * Returns whether command is a bye command.
     *
     * @return false by default.
     */
    public boolean isExit() {
        return false;
    }
}
