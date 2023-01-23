public class UnmarkCmd extends Command {
    int index;
    Task task;

    public UnmarkCmd(TaskList taskList, String line) {
        super(taskList, line);
        this.index = Parser.parseMarkUnmarkDelete(line);
    }

    public void execute() {
        this.task = taskList.get(this.index).unmarkDone();
        uiReply();
    }

    public void uiReply() {
        String output = "Ok, I've marked this task as not done yet:";
        Ui.displayMsg(output + "\n" + Ui.indentString(this.task.toString(), 1));
    }
}
