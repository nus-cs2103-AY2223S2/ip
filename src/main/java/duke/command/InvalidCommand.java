package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * InvalidCommand is to help indicate that the input command cannot be used
 */
public class InvalidCommand extends Command{

    /**
     * executes the purpose of the InvalidCommand
     * @param taskList can be ignored but is required due to the abstract class
     * @param storage can be ignored but is required due to the abstract class
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        //do nothing
    }

    /**
     * indication if the command should end the program
     * @return false as addCommand is not an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
