package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Todo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Adds to do task to TaskList.
 */
public class AddTodoCommand extends Command {
    private String details;
    public AddTodoCommand(String details) {
        this.details = details;
    }
    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo addition = new Todo(details);
        tasks.add(addition);
        ui.showAddTask(addition);
        ui.showTaskCount(tasks.size());
    }
}
