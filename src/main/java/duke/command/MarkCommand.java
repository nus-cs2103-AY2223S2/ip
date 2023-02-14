package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;


/**
 * MarkCommand marks the specific task as completed.
 */
public class MarkCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 5;

    /**
     * Creates a MarkCommand.
     *
     * @param textCmd user input.
     */
    public MarkCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @param taskList List of tasks.
     * @return String output to be displayed by Chatbot.
     * @throws DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task markedTask = taskList.markTask(Parser.stringToInt(textCmd.substring(START_INDEX_OF_DESCRIPTION)));
        return ui.printMarkTask(markedTask);
    }
}
