package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand exits the program.
 */
public class ExitCommand extends Command {


    /**
     * Creates an ExitCommand.
     *
     * @param textCmd user input.
     */
    public ExitCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Exits the program.
     *
     * @param taskList task list containing all existing tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @throws  DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        this.isExit = true;
        return ui.printBye();
    }
}
