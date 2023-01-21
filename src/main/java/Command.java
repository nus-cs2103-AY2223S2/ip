public abstract class Command {
    protected TaskList taskList;
    protected UI ui;

    public Command() {
    }

    public void assign(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract void execute() throws DukeException;

}
