package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

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
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: description.
     * @throws DukeException If failed to save new task list to storage.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        String description = args[0];

        addTask(new ToDo(description), ui, taskList, storage);
    }
}
