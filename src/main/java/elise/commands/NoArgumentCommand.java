package elise.commands;

import elise.EliseException;
import elise.internal.Storage;
import elise.internal.TaskList;
import elise.internal.Ui;

/**
 * Command with no argument.
 */
public class NoArgumentCommand implements Command {

    private final int code;

    /**
     * Constructor for Command of specified code.
     *
     * @param code unique code for type of command.
     */
    public NoArgumentCommand(int code) {
        this.code = code;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws EliseException {
        if (code == -1) {
            return ui.wrapText("Bye");
        } else if (code == 0) {
            return ui.showHelp();
        } else if (code == 1) {
            return ui.wrapText(taskList.list());
        } else {
            throw new EliseException("Invalid code.");
        }
    }
}
