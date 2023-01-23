public class MarkCmd extends Command {
    int index;
    Task task;

    public MarkCmd(TaskList taskList, int index) {
        super(taskList);
        this.index = index;
    }

    public void execute() {
        this.task = taskList.get(this.index).markDone();
        uiReply();
    }

    public void uiReply() {
        String output = "Nice! I've marked this task as done:";
        Ui.displayMsg(output + "\n" + Ui.indentString(this.task.toString(), 1));
    }
}
