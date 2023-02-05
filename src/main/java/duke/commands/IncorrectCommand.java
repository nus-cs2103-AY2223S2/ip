package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents an invalid command.
 */
public class IncorrectCommand extends Command {

    private static final String INT_ERROR = "COME ONNNN now I asked for an number";
    private static final String INVALID_COMMAND_ERROR = "HUH?? "
            + "U DRUNK? ";
    private static final String TASK_DESC_ERROR = "For the LAST time?"
            + "\n----[todo] description"
            + "\n----[deadline] description /by deadline"
            + "\n----[event] description /from start /to end";
    private static final String NO_DATE_ERROR = "Give me a dateeeeee";
    private static final String NO_KEYWORD_ERROR = "I need a keyword to start looking y'know??";
    private static final String WRONG_DATE_FORMAT_ERROR = "WRONG!!1! I need (dd/mm/yyyy hh:ss)";
    private final String errorType;

    public IncorrectCommand(String commandType) {
        this.errorType = commandType;
    }

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage) {
        switch (errorType) {
        case "mark":
        case "unmark":
        case "delete":
            return INT_ERROR;
        case "invalid":
            return INVALID_COMMAND_ERROR;
        case "task":
            return TASK_DESC_ERROR;
        case "date":
            return NO_DATE_ERROR;
        case "find":
            return NO_KEYWORD_ERROR;
        case "dt format":
            return WRONG_DATE_FORMAT_ERROR;
        default:
            return "";
        }
    }
}
