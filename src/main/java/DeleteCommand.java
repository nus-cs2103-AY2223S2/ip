public class DeleteCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private int taskId;

    public DeleteCommand(Ui ui, TaskList taskList, int taskId) {
        this.ui = ui;
        this.taskList = taskList;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
       taskList.remove(taskId);
    }
}
