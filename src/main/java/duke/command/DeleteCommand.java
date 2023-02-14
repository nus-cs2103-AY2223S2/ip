package duke.command;

import static duke.command.CommandValidations.validateNotEmptyArgs;
import static duke.command.CommandValidations.validateTaskIndex;
import static duke.util.Ui.getUi;

import duke.DukeException;
import duke.task.TaskList;

/**
 * A command that deletes a Task from the current store.
 * @author Junyi
 */
public class DeleteCommand extends Command {

    private final TaskList taskList;
    private final int taskIndex;

    /**
     * Constructor for DeleteCommand.
     * Deletes a task from the task list.
     * @param taskList TaskList of Duke's tasks.
     * @param taskIndex Index of the task in the task list.
     */
    private DeleteCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * Factory method to create delete command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of DeleteCommand.
     */
    public static DeleteCommand createDeleteCommand(String inputString, TaskList taskList) throws DukeException {
        validateNotEmptyArgs(inputString);
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
        validateTaskIndex(taskIndex, taskList);

        return new DeleteCommand(taskList, taskIndex);
    }

    @Override
    public String execute() throws DukeException {
        taskList.remove(taskIndex);

        return getUi().showTaskDeletedMessage();
    }
}
