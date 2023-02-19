package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to unmark a task (i.e mark task as not done).
 */
public class UnmarkCommand implements Command {
    @Override
    public String getCommandName() {
        return "unmark";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^unmark ([0-9]+)$";
    }

    @Override
    public String getCommandPattern() {
        return "unmark <taskNo>";
    }

    /**
     * Unmarks task in task list.
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
            Task task = taskList.getTask(taskNo);
            task.setIsDone(false);

            ui.showLine();
            ui.showText("Okay Sir! I've marked this task as not done yet:");
            ui.showText(task.toString());
            ui.showLine();

            storage.save(taskList.getAllTasks());
        } else {
            ui.showLine();
            ui.showText("Invalid task number!");
            ui.showLine();
        }
    }
}
