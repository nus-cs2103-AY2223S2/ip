public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task task = taskList.getTask(index);
        task.removeTask();
        taskList.removeListMember(index);
        storage.deleteFromFile(index);
        ui.removeTaskMsg(task, Task.totalTask());
    }

}
