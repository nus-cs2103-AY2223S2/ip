package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * Command: Creates a Todo Task
 */
public class TodoCommand extends Command {
    private String title;

    /**
     * Creates a todo task command
     *
     * @param title of the Task that that is being created
     */
    public TodoCommand(String title) {
        this.title = title;
    }

    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Todo(this.title);
        tasks.add(newTask);
        ui.showAdd(newTask);
        storage.save(tasks);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
