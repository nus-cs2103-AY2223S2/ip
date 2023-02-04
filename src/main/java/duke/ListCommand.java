package duke;

/**
 * Command class for listing out
 */
public class ListCommand implements Command {
    ListCommand() {}
    @Override
    public String execute(TaskList taskList) {
        return taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
