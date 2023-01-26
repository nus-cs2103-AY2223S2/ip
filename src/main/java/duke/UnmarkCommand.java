package duke;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(input);

        Task unmarkedTask = taskList.changeMarkStatus(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskUnmarked(unmarkedTask);
        ui.showNumTasks(taskList);
    }
}
