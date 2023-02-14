package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;
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
     * @throws DukeException
     */
    public TodoCommand(String... input) throws DukeException {
        try {
            this.title = input[0];
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Views.MISSING_ARGS_ERR_STRING);
        }
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Task newTask = new Todo(this.title);
        tasks.add(newTask);
        Ui.showAdd(newTask);
        storage.save(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        Task newTask = new Todo(this.title);
        tasks.add(newTask);
        storage.save(tasks);
        return Ui.stringAdd(newTask);
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
