package duke;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(input);

        // Task removedTask = taskList.getTaskList().get(taskNumber);
        Task removedTask = taskList.deleteTask(taskNumber);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskRemoved(removedTask);
        ui.showNumTasks(taskList);
    }
}
