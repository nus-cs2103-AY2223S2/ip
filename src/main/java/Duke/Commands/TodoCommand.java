package Duke.Commands;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.Tasks.Todo;
import Duke.DukeExceptions.DukeInvalidInputException;

public class TodoCommand extends Command {
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException {
        String response = tasks.add(Todo.createTodo(input));
        storage.saveState(tasks);
        ui.printResponse(response);
    }
}
