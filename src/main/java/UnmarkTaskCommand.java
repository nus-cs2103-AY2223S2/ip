public class UnmarkTaskCommand extends Command {
    private int taskNumber;

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws DukeException {
        if (taskNumber >= taskList.getSize()) {
            throw new DukeException("Task number is out of range!");
        }
        Task task = taskList.getTask(taskNumber);
        task.markAsNotDone();
        ui.printTaskUnmarked(task);
    }
}
