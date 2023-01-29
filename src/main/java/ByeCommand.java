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
