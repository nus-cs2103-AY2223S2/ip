package duke;

/**
 * A command to mark tasks as completed.
 */
public class MarkCommand extends Command {

    /**
     * Constructs the mark command with given user input.
     *
     * @param input Input from user.
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the mark command given task list,
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(input);

        Task markedTask = taskList.changeMarkStatus(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskMarked(markedTask);
        ui.showNumTasks(taskList);
    }

    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(input);

        Task markedTask = taskList.changeMarkStatus(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());

        return ui.formatTaskMarked(markedTask, taskList);
    }
}
