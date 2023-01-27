package command;

import duke.DukeException;
import task.TaskList;
import ui.TextUi;

/**
 * Command for exiting from the program
 */
public class ExitCommand extends CommandClass {

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     */
    public ExitCommand(String command, boolean doesPrint) {
        super(command, doesPrint, true);
    }

    /**
     * Execute the command
     * @param taskList the list of tasks
     * @param ui       a text UI
     * @throws DukeException
     */
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        throw new DukeException("execute method not implemented for ExitCommand");
    }
}
