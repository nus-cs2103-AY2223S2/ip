public class MarkCommand extends Command {
    int taskIdToMark;

    public MarkCommand(int taskIdToMark) {
        this.taskIdToMark = taskIdToMark;
    }
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.mark(taskIdToMark);
    }
}
