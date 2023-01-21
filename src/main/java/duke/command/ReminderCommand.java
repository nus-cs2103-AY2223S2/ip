package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ReminderCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList deadlineList = tasks.extractDeadlines();

        StringBuilder listContent = new StringBuilder("Here are upcoming deadlines:\n");
        for (int i = 0; i < deadlineList.getNoOfTasks(); i++) {
            listContent.append(i + 1).append(".").append(deadlineList.getTask(i)).append("\n");
        }

        String message = String.valueOf(listContent);
        ui.appendResponse(message);
    }
}
