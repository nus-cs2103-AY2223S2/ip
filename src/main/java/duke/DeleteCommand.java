package duke;

/**
 * Command class for delete
 */
public class DeleteCommand implements Command {
    private int deleteIndex;
    DeleteCommand(int i) {
        deleteIndex = i;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.delete(deleteIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
