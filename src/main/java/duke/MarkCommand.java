package duke;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(input);

        Task markedTask = taskList.changeMarkStatus(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskMarked(markedTask);
        ui.showNumTasks(taskList);
    }
}
