package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command printing the message of an <code>Exception</code>,
 * typically as the result of parsing invalid command arguments.
 */
public class ExceptionPrint implements Command {
    private Exception ex;

    /**
     * Creates an exception-print command.
     *
     * @param ex exception with message to be printed
     */
    public ExceptionPrint(Exception ex) {
        this.ex = ex;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks tasklist to perform the action on
     * @param ui ui to perform the action on
     * @param storage storage to perform the action on
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.print("?!?!? " + ex.getMessage());
    }
}
