public class UnmarkCommand extends Command{
    TaskList taskList;
    int taskIdToUnmark;

    public UnmarkCommand(TaskList taskList, int taskIdToUnmark) {
        this.taskList = taskList;
        this.taskIdToUnmark = taskIdToUnmark;
    }
    @Override
    public void execute() {
        taskList.unmark(taskIdToUnmark);
    }
}
