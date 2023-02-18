package duke;

/**
 * A command to mark tasks as not completed.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs the unmark command with given user input.
     * @param input Input from user.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the unmark command given task list,
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(this.getInput());

        Task unmarkedTask = taskList.changeMarkStatus(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskUnmarked(unmarkedTask);
        ui.showNumTasks(taskList);
    }

    /**
     * Executes the Unmark command with given task list,
     * ui and storage, and also returns output String for bot.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @return Formatted output message.
     * @throws DukeException If error occurs.
     */
    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(this.getInput());

        Task unmarkedTask = taskList.changeMarkStatus(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());

        return ui.formatTaskUnmarked(unmarkedTask, taskList);
    }
}
