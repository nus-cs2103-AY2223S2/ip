public abstract class Command {

    protected final TaskList taskList;
    private final boolean isExit;

    Command(TaskList taskList, boolean isExit) {
        this.taskList = taskList;
        this.isExit = isExit;
    }

    public boolean isExitCommand() {
        return isExit;
    }

    public abstract void execute() throws DukeException;

}
