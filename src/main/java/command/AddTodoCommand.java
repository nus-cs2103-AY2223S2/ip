package command;

import command.Command;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class AddTodoCommand extends Command {

    private String description;
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = tasks.addToDo(description);
        ui.formResponse("New todo task added: " + todo);
        storage.save(tasks.getList());
    }

}
