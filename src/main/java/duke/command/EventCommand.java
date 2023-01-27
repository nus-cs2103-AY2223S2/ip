package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    private Event event;
    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.addTasks(event);
            storage.updateStorage();
            ui.printAddTask(taskList, t);
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
