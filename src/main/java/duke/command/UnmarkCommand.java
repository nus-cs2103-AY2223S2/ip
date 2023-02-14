package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * UnmarkCommand unmarks the specific task.
 */
public class UnmarkCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 7;

    /**
     * Creates an UnmarkCommand.
     *
     * @param textCmd user input.
     */
    public UnmarkCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Unmarks the task at the specified index as done.
     *
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @param taskList List of tasks.
     * @return String output to be displayed by Chatbot.
     * @throws DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(Parser.stringToInt(textCmd.substring(START_INDEX_OF_DESCRIPTION)));
        return ui.printUnmarkTask(unmarkedTask);
    }
}
