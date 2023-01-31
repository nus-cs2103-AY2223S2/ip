public class UnmarkCommand extends Command{
    int taskIdToUnmark;

    public UnmarkCommand(int taskIdToUnmark) {
        this.taskIdToUnmark = taskIdToUnmark;
    }
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.unmark(taskIdToUnmark);
    }
}
