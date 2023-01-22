public class DeleteCommand extends Command{
    protected int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
            tasks.delete(taskNumber);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
