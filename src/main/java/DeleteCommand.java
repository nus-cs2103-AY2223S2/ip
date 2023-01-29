public class DeleteCommand implements Command {
    private int deleteIndex;
    DeleteCommand(int i) {
        deleteIndex = i;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.delete(deleteIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
