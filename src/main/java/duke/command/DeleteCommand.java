package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand implements Command {
    @Override
    public String getCommandName() {
        return "delete";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^delete ([0-9]+)$";
    }

    @Override
    public String getCommandPattern() {
        return "delete <taskNo>";
    }

    /**
     * Deletes task from task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: task number (whole number format).
     * @throws DukeException If task number < 0 or > total tasks or invalid task number.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        // Assert arguments has only 1 item: task number.
        assert args.length == 1;

        int taskNo = Integer.parseInt(args[0]);

        if (taskNo > 0 && taskNo <= taskList.getTotalTasks()) {
            Task removedTask = taskList.deleteTask(taskNo);

            ui.showLine();
            ui.showText("Yes Sir! I've removed this task:");
            ui.showText(removedTask.toString());
            ui.showText(String.format("Now you have %d tasks in the list.", taskList.getTotalTasks()));
            ui.showLine();

            storage.save(taskList.getAllTasks());
        } else {
            ui.showLine();
            ui.showText("Invalid task number!");
            ui.showLine();
        }
    }
}
