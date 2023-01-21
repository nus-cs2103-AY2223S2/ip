public class MarkTaskCommand extends Command {
    private int taskNumber;

    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws DukeException {
        if (taskNumber >= taskList.getSize()) {
            throw new DukeException("Task number is out of range!");
        }
        Task task = taskList.getTask(taskNumber);
        task.markAsDone();
        ui.printTaskMarked(task);
    }
}
