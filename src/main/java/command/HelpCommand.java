package command;

import task.TaskManager;
import util.CommandMenu;
import util.DukeException;

/**
 * Executes help command which displays all commands and how to use them.
 */
public class HelpCommand extends Command {

    public HelpCommand() {}

    /**
     * Displays the command menu when prompted by user.
     * <p>
     * @param taskManager
     * @return all commands
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        return CommandMenu.displayCommandMenu();
    }
}
