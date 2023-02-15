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
     * @param error types(enum) of error
     * @param name  name of the error
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
        String format = ui.addCommandsFormat(this.name);
        switch(error) {
        case emptyDesc:
            return "Sorry ! " + name + " description must not be empty" + format;
        case dateTime:
            return "Sorry ! " + name + " datetime format must be in dd/MM/yyyy:HHmm" + format;
        case missingBy:
            return "Sorry ! " + name + " missing /by in your input" + format;
        case missingFrom:
            return "Sorry ! " + name + " missing /from in your input" + format;
        case missingTo:
            return "Sorry ! " + name + " missing /to in your input" + format;
        default:
            return ui.showLoadingError();
        }
    }
}
