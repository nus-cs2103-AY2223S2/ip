package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {
    private final boolean toMarkorUnmark;
    private final int taskIndex;

    public MarkCommand(boolean toMarkorUnmark, int taskIndex) {
        this.toMarkorUnmark = toMarkorUnmark;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex >= tasks.size()) {
            String errorMessage = String.format("Task %d does not exist!", taskIndex + 1);
            throw new DukeException(errorMessage);
        } else {
            Task t = tasks.get(taskIndex);
            if (toMarkorUnmark) {
                t.mark();
                ui.showMessage("Congratulations for completing the task ^^ I've marked it as done:");
            } else {
                t.unmark();
                ui.showMessage("Ok, I've unmarked the task for you:");
            }
            ui.showMessage(t.toString());
            storage.saveTasks(tasks.getTasks());
        }
    }
}
