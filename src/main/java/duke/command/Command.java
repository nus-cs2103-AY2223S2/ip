package duke.command;

import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent an executable command.
 */
public abstract class Command {

    /**
     * Return true if it is exit command.
     *
     * @return true if it is exit command.
     */
    public abstract boolean isExit();

    /**
     * Execute the program given a specific command.
     *
     * @param task list that store the task.
     * @param ui User Interface of the application.
     * @param storage Database that store previous undelete task.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws DukeIoException indicate failed or interrupted I/O operations occurred.
     */
    public abstract String execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException,
            DukeIoException, DukeEventOverlapException;

    //@@author Jiayan-Lim-reused
    //Reused from
    //https://stackoverflow.com/questions/6810336/is-there-a-way-in-java-to-convert-an-integer-to-its-ordinal-name
    //with minor modifications
    public static String getOrdinalFor(int value) {
        int tenRemainder = value % 10;
        switch (tenRemainder) {
        case 1:
            return value + "st";
        case 2:
            return value + "nd";
        case 3:
            return value + "rd";
        default:
            return value + "th";
        }
    }
    //@@author
}
