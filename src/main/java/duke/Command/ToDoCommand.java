package duke.Command;

import duke.Task.ToDo;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The ToDo command which is executed by duke.Duke.
 */
public class ToDoCommand extends Command {

    private final String name;

    /**
     * The constructor for an (executable) ToDo command.
     * @param name The name of the todo item.
     */
    public ToDoCommand(String name) {
        this.name = name;
    }

    /**
     * Creates a new ToDo task which is added to the task list.
     * The confirmation is then displayed on the UI, and the changes to task list are saved to file.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        ToDo td = new ToDo(name);
        String confirmationMessage = tasks.addTask(td);
        storage.saveToFile(tasks.getTasks());
        return confirmationMessage;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
