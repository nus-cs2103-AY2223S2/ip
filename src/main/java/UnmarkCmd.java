public class UnmarkCmd extends Command {

    int index;
    Task task;

    public UnmarkCmd(TaskList taskList, int index) {
        super(taskList);
        this.index = index;
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
