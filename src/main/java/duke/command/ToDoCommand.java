package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * ToDo Command is a command that creates a new todo task.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * A Constructor for ToDoCommand.
     *
     * @param description Description of ToDo task.
     */
    public ToDoCommand(String description) {
        super(false);
        this.description = description;

    }

    /**
     * Adds the todo task into tasklist.
     *
     * @param tasks Tasklist containing the list of tasks, displays the add message to user.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addToDo(description);
        int size = tasks.getSize();
        Task temp = tasks.getTask(size - 1);
        storage.saveTasksToFile(tasks.getListOfTasks());
        return ui.showAdd(temp, size);
    }
}
