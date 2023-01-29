package command;

import java.util.Arrays;

import dukeexception.CommandException;
import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

public class ToDoCommand extends Command {

    public ToDoCommand(String request) {
        super(request);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            String description = values[0];

            Task task = new Todo(description);
            tasks.add(task);

            ui.showTaskAdded(task, tasks.size());

            storage.save(tasks);
        } catch (CommandException error) {
            ui.showError(error);
        }
    }

    @Override
    public String[] unwrap() throws CommandException {
        String[] values = super.getRequest().split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkRequestRequirement();

        String description = String.join(" ", Arrays.copyOfRange(values, 1, values.length));
        return new String[] { description };
    }

    @Override
    public void checkRequestRequirement() throws CommandException {
        String message = "";

        if (super.getRequest().split(" ").length <= 1) {
            message = "Description cannot be empty";
        }

        if (!message.isEmpty()) {
            throw new CommandException(message);
        }
    }
}
