package spongebob.command;

import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobIoException;
import spongebob.exception.SpongebobRepeatedCommandException;

import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Return true if it is exit command.
     *
     * @return true if it is exit command.
     */
    public abstract boolean isExit();

    /**
     * Executes the program given a specific command.
     *
     * @param task list that store the task.
     * @param ui User Interface of the application.
     * @param storage Database that store previous undelete task.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws SpongebobIoException indicate failed or interrupted I/O operations occurred.
     * @throws SpongebobEventOverlapException indicate there are events overlap with each other.
     * @throws SpongebobRepeatedCommandException indicate that user repeat same command given before.
     */
    public abstract String execute(TaskList task, Ui ui, Storage storage) throws SpongebobInvalidArgumentException,
            SpongebobIoException, SpongebobEventOverlapException, SpongebobRepeatedCommandException;

    // @@author Jiayan-Lim-reused
    // Reused from
    // https://stackoverflow.com/questions/6810336/is-there-a-way-in-java-to-convert-an-integer-to-its-ordinal-name
    // with minor modifications
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
    // @@author
}
