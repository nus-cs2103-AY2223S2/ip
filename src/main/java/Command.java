public abstract class Command {

    TaskList taskList;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract void execute();
    public abstract void uiReply();
}
