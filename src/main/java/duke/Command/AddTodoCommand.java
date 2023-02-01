package duke.Command;

import duke.Exceptions.DukeException;
import duke.Storage;
import duke.Tasks.ToDo;
import duke.Tasks.TaskList;
import duke.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ToDo newTodo = new ToDo(this.description);
        tasks.addingTask(newTodo);
        ui.showTaskAdded(newTodo, tasks);
        return newTodo.toString();
    }
}
