package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Todoclass to indicate a task needed to be completed.
 */
public class TodoCommand extends Command {
    private String message;

    public TodoCommand(String fullCommand) {
        String[] checker = fullCommand.split("todo ");
        this.message = checker[1];
    }

    public String execute(TaskList tasks, Ui ui, StorageList storage) {
        Todo t = new Todo(message);
        tasks.addToList(t);
        return "Got it, I've added this task:\n" + t + "\n" + tasks.statement();
    }
}
