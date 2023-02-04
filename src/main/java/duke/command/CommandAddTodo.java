package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a Todo command.
 */
public class CommandAddTodo extends Command {

    private String description;

    public CommandAddTodo(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks.getList());
        return ui.formResponse("New todo task added: " + todo);
    }

}
