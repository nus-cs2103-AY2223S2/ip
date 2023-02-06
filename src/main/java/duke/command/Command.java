package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that can be executed.
 * Other commands extend this class.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     * @throws DukeException If there is an error in executing the command.
     */
    public abstract String execute(TaskList tl, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}

/*
To do:
Remember the datetime formatting for the code if they want to add datetime is "d/MM/yyyy HHmm"
 */
