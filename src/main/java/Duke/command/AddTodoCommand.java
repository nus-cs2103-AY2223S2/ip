package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.Tasks.Todo;
import Duke.TaskList;
import Duke.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructor that uses to create Todo instance.
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeMainExceptions {
        Todo newTodo = new Todo(this.description);
        tasks.addTask(newTodo, storage);
        return ui.printAddedTask(newTodo, tasks);
    }
}
