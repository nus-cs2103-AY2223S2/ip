public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
       taskList.remove(taskId);
    }
}
