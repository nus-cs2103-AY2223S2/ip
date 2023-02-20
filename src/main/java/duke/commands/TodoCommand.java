package duke.commands;

import java.util.Arrays;
import duke.exceptions.DukeInvalidTodoCommandException;
import duke.tasks.TodoTask;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidTodoCommandException {

        String[] taskNameArray = Arrays.copyOfRange(tokens, 1, tokens.length);

        if (taskNameArray.length == 0) {
            throw new DukeInvalidTodoCommandException();
        }

        String taskName = String.join(" ", taskNameArray);
        TodoTask newTodoTask = new TodoTask(taskName);

        taskList.addTask(newTodoTask);
        storage.saveTaskList(taskList);

        String numTasksString = ui.formatNumberOfTasksAsString(taskList.getSize());
        return "Added:\n" + newTodoTask + "\n" + numTasksString;
    }

    public boolean isByeCommand() {
        return false;
    }
}
