package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Exit Command.
 */
public class ExitCommand extends Command {


    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor.
     */
    public ExitCommand() {

    }

    /**
     * Executes the command.
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    /**
     * Checks if command exits.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
