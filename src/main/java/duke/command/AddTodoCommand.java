package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddTodoCommand extends Command {

    private String description;
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = tasks.addToDo(description);
        ui.formResponse("New todo duke.task added: " + todo);
        storage.save(tasks.getList());
    }

}
