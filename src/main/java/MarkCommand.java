public class MarkCommand extends Command {
    TaskList taskList;
    int taskIdToMark;

    public MarkCommand(TaskList taskList, int taskIdToMark) {
        this.taskList = taskList;
        this.taskIdToMark = taskIdToMark;
    }
    @Override
    public void execute() {
        taskList.mark(taskIdToMark);
    }
}
