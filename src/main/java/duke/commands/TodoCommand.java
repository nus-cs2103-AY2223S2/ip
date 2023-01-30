package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Todo;
import duke.exceptions.DukeInvalidInputException;

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
