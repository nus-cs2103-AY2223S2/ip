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
        return "^todo (.*) \\/place (.*)$";
    }

    @Override
    public String getCommandPattern() {
        return "todo <description> /place <location>";
    }

    /**
     * Adds todo task from input to the task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: description, place.
     * @throws DukeException If failed to save new task list to storage.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        // Assert arguments has only 2 items: description, place.
        assert args.length == 2;

        String description = args[0];
        String place = args[1];

        addTask(new ToDo(description, place), ui, taskList, storage);
    }
}
