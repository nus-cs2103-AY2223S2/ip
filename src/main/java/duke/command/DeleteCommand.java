package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * DeleteCommand deletes a specific task provided by user.
 */
public class DeleteCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 7;

    /**
     * Creates a DeleteCommand.
     *
     * @param textCmd user input.
     */
    public DeleteCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Deletes a specific task provided by the user.
     *
     * @param taskList task list containing all existing tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by our Chatbot
     * @throws DukeException
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task removedTask = taskList.deleteTask(
                Parser.stringToInt(textCmd.substring(START_INDEX_OF_DESCRIPTION)));
        return ui.printDeleteTask(removedTask, taskList.getNumTasks());
    }
}
