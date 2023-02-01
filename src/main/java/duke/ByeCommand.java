package duke;

/**
 * This command doesn't do anything to task list
 * Instead, it set exit as true and will shut down process in duke class
 */
public class ByeCommand implements Command {
    ByeCommand() {}

    @Override
    public void execute(TaskList taskList) {
        taskList.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
