public class DeleteCmd extends Command {
    String lineInput;
    Task task;

    public DeleteCmd(TaskList taskList, String lineInput) {
        super(taskList);
        this.lineInput = lineInput;
    } 

    public void execute() {
        this.task = this.taskList.removeTask(this.lineInput);
        uiReply();
    }

    public void uiReply() {
        Ui.displayMsg("Noted. I've removed this task:\n" + Ui.indentString(this.task.toString(), 1) + "\n" + taskList.countTasks());
    }

}
