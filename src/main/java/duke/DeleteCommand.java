package duke;

/**
 * A command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs the delete command with given user input.
     *
     * @param input Input from user.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the delete command with given task list,
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(this.getInput());

        Task removedTask = taskList.deleteTask(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskRemoved(removedTask);
        ui.showNumTasks(taskList);
    }

    /**
     * Executes the delete command with given task list,
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

        Task removedTask = taskList.deleteTask(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());

        return ui.formatTaskRemoved(removedTask, taskList);
    }
}
