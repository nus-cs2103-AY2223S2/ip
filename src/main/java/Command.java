public abstract class Command {

    TaskList taskList;
    String lineInput;

    public Command(TaskList taskList, String lineInput) {
        this.taskList = taskList;
        this.lineInput = lineInput;
    }

    public abstract void execute();
    public abstract void uiReply();
}
