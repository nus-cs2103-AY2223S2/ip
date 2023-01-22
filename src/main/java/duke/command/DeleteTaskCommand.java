package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteTaskCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    private Storage storage;

    public DeleteTaskCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = this.commandMessage.split(" ", 2);
            int taskNumber = Integer.parseInt(commandMessageArr[1]);
            Task task = this.taskList.deleteTask(taskNumber);
            this.storage.restructure(this.taskList);
            this.ui.replyTaskDeleted(task);
        } catch (IOException | DukeException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
