package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Command to add todo task.
 */
public class ToDoCommand extends AddTaskCommand {
    @Override
    public String getCommandName() {
        return "todo";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^todo (.*)$";
    }

    @Override
    public String getCommandPattern() {
        return "todo <description>";
    }

    /**
     * Adds todo task from input to the task list.
     *
     * @param args Argument list in order: description.
     * @param ui User interface.
     * @param taskList Task list.
     * @param storage Storage.
     * @throws DukeException If failed to save new task list to storage.
     */
    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String description = args[0];

        addTask(new ToDo(description), ui, taskList, storage);
    }
}
