public class MarkCommand extends Command {

    private final int taskNumber;

    MarkCommand(TaskList taskList, int taskNumber) {
        super(taskList, false);
        this.taskNumber = taskNumber;

    }

    @Override
    public void execute() throws DukeException {
        super.taskList.markTask(this.taskNumber);
    }

}
