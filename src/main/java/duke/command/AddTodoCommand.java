package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ToDo newTodo = new ToDo(this.description);
        tasks.addTask(newTodo, storage);
        return ui.showTaskAdded(newTodo, tasks);
    }
}
