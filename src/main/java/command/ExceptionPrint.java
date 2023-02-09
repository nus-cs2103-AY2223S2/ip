package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command printing the message of an <code>Exception</code>,
 * typically as the result of parsing invalid command arguments.
 */
public class ExceptionPrint implements Command {
    private final Exception ex;

    /**
     * Creates an exception-print command.
     *
     * @param ex exception with message to be printed.
     */
    public ExceptionPrint(Exception ex) {
        this.ex = ex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printMiki("?!?!? " + ex.getMessage());
    }
}
