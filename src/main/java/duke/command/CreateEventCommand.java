package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class CreateEventCommand extends Command {
    private Ui ui;

    private TaskList taskList;

    private Storage storage;

    public CreateEventCommand(String commandMessage, Ui ui, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public boolean execute() {
        try {
            String[] commandMessageArr = this.commandMessage.split("/", 3);
            Task task = new Event(commandMessageArr[0].substring(6), false,
                    commandMessageArr[1].substring(5).trim(),
                    commandMessageArr[2].substring(3));
            this.taskList.addTask(task);
            this.storage.storeTask(task);
            this.ui.replyTaskAdded(task);
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }

        return false;
    }
}
