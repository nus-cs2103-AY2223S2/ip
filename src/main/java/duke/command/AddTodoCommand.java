package duke.command;

import duke.*;
import duke.task.TaskList;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        Ui.ShowAddMessage(todo, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}