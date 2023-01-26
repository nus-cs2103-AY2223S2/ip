package duke.commands;

import duke.exceptions.DukeInvalidTodoCommandException;
import duke.tasks.TodoTask;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import java.util.Arrays;

/**
 * The {@code Command} class for the {@code todo} command.
 */
public class TodoCommand extends Command {

    private final String[] tokens;

    /**
     * Instantiates a new {@code TodoCommand} object.
     *
     * @param tokens The array of strings generated from tokenising the user's input.
     */
    public TodoCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DukeInvalidTodoCommandException If the {@code todo} command is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidTodoCommandException {

        String[] taskNameArray = Arrays.copyOfRange(tokens, 1, tokens.length);

        if (taskNameArray.length == 0) {
            throw new DukeInvalidTodoCommandException();
        }

        String taskName = String.join(" ", taskNameArray);
        TodoTask newTodoTask = new TodoTask(taskName);

        taskList.addTask(newTodoTask);
        ui.showMessage("Added:\n" + newTodoTask);
        ui.showNumberOfTasks(taskList.getSize());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
