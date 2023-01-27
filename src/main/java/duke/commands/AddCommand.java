package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.tasks.Todo;
import duke.ui.Ui;

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
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        tasks.addToList(this.task, false);
    }
}
