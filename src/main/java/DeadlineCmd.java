public class DeadlineCmd extends Command {
    TaskList taskList;
    String lineInput;
    Task deadline;

    public DeadlineCmd(TaskList taskList, String lineInput) {
        super(taskList);
        this.lineInput = lineInput;
    }

    public void execute() {
        try { 
            this.deadline = Deadline.create(this.lineInput);
            taskList.add(this.deadline);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
    };
    
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n" + 
        Ui.indentString(this.deadline.toString(), 1) + "\n" + taskList.countTasks());
    };
}
