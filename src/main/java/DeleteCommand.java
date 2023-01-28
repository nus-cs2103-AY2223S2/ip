public class DeleteCommand extends Command {

    private final int taskNumber;

    DeleteCommand(TaskList taskList, int taskNumber) {
        super(taskList, false);
        this.taskNumber = taskNumber;

    }

    @Override
    public void execute() throws DukeException {
        super.taskList.deleteTask(this.taskNumber);
    }

}
