package duke.command;

import duke.exception.EmptyDescException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private Deadline deadline;
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.addTasks(deadline);
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
