public class MarkCommand implements Command{
    private int markIndex;
    MarkCommand(int i) {
        markIndex = i;
    }
    @Override
    public void execute(TaskList taskList) {
        taskList.mark(markIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
