package command;

import duke.DukeException;
import task.TaskList;
import ui.TextUi;

/**
 * Command for exiting from the program
 */
public class ExitCommand extends Command {

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     */
    public ExitCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 1);
        isExit = true;
    }

    /**
     * Execute the command
     * @param taskList the list of tasks
     * @param ui       a text UI
     * @throws DukeException
     */
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        ui.sayGoodbye();
    }

    public String execute(TaskList taskList) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
