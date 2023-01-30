package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDoTask;
import duke.ui.Ui;

/**
 * Represents a command to add a Todo task to a tasklist.
 */
public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new ToDoTask(description);
        taskList.add(task);
        return "added: " + task;
    }
}
