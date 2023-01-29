public class UnMarkCommand implements Command{
    private int unMarkIndex;
    UnMarkCommand(int i) {
        unMarkIndex = i;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.unmark(unMarkIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
