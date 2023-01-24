package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class UnmarkTaskCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    private Storage storage;

    public UnmarkTaskCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = commandMessage.split(" ", 2);
            int taskNumber = Integer.parseInt(commandMessageArr[1]);
            Task task = taskList.unmarkTask(taskNumber);

            storage.restructure(taskList);
            ui.replyTaskUnmarked(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
