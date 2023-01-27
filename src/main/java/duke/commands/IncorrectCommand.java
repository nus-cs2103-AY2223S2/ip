package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public class IncorrectCommand extends Command {


    private static final String INT_ERROR = "Come on now, try again. I asked for an number";
    private static final String INVALID_COMMAND_ERROR = "My bad, didn't catch what you said, "
            + "did you mess up your spelling? 0_o";
    private static final String TASK_DESC_ERROR = "Sure you provided everything?\n I need:"
            + "\n----Description for [todo]"
            + "\n----Description, /by deadline for [deadline]"
            + "\n----Description, /from start date time /to end date time for [event]";
    private static final String NO_DATE_ERROR = "Give me a dateeeeee";

    private final String errorType;

    public IncorrectCommand(String commandType) {
        this.errorType = commandType;
    }

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        switch (errorType) {
        case "mark":
        case "unmark":
        case "delete":
            System.out.println(INT_ERROR);
            break;
        case "invalid":
            System.out.println(INVALID_COMMAND_ERROR);
            break;
        case "task":
            System.out.println(TASK_DESC_ERROR);
            break;
        case "date":
            System.out.println(NO_DATE_ERROR);
            break;
        default:
            break;
        }
    }
}
