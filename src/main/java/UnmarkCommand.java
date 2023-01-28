public class UnmarkCommand extends Command {

    private final int taskNumber;

    UnmarkCommand(TaskList taskList, int taskNumber) {
        super(taskList, false);
        this.taskNumber = taskNumber;

    }

    @Override
    public void execute() throws DukeException {
        super.taskList.unmarkTask(this.taskNumber);
    }

}
