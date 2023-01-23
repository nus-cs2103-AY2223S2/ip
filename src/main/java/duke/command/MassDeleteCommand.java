package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

public class MassDeleteCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        for (int j = 0; j < tasks.getNoOfTasks(); j++) {
            DukeTask currentTask = tasks.getTask(j);
            if (currentTask.getStatus()) {
                tasks.deleteTask(j);
            }
        }

        StringBuilder listContent = new StringBuilder("I have deleted all the tasks that have been marked as done\n"
                + "Here are the remaining tasks in your list:\n");
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            listContent.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }

        String message = String.valueOf(listContent);
        ui.appendResponse(message);
    }
}
