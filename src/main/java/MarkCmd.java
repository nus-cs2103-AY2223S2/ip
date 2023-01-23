public class MarkCmd extends Command {
    int index;
    Task task;

    public MarkCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.index = Parser.parseMarkUnmarkDelete(lineInput);
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
