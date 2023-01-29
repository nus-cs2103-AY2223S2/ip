package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {

    private String description;
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new ToDo(description);
        tasks.addTask(todo);
        ui.formResponse("New todo task added: " + todo);
        storage.save(tasks.getList());
    }

}
