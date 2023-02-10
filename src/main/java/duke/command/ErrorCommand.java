package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * ErrorCommand - when user enters a Command, and it
 *         contains one or more errors in it.
 */
public class ErrorCommand extends Command {

    /**
     * Types of Errors in Duke
     */
    public enum types {
        emptyDesc,
        dateTime,
        missingBy,
        missingFrom,
        missingTo

    }

    private types error;
    private String name;
    /**
     * Constructor
     */
    public ErrorCommand(types error, String name) {
        this.error = error;
        this.name = name;
    }

    /**
     * A helper command to determine which error the user is facing
     * @return an error message depicting what the error is, and the correct format required
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        switch(error) {
        case emptyDesc:
            return "Sorry ! " + name + " description must not be empty";
        case dateTime:
            return "Sorry ! " + name + " datetime format must be in dd/MM/yyyy:HHmm";
        case missingBy:
            return "Sorry ! " + name + " missing /by in your input";
        case missingFrom:
            return "Sorry ! " + name + "missing /from in your input";
        case missingTo:
            return "Sorry ! " + name + "missing /to in your input";
        default:
            return ui.showLoadingError();
        }
    }
}
