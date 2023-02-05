package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Represents the command for adding a task to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    public AddCommand(String description) {
        this.task = new Todo(description);
    }

    public AddCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    public AddCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage) {
        return tasks.addToList(this.task, false);
    }
}
