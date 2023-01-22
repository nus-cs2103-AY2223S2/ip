package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.IOException;

public class CreateTodoCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    private Storage storage;

    public CreateTodoCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = this.commandMessage.split(" ", 2);
            Task task = new Todo(commandMessageArr[1], false);
            this.taskList.addTask(task);
            this.storage.storeTask(task);
            this.ui.replyTaskAdded(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
