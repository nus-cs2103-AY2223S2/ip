package duke;

/**
 * Command class for listing out
 */
public class ListCommand implements Command {
    ListCommand() {}
    @Override
    public void execute(TaskList taskList) {
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
