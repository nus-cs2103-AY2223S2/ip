public class EventCmd extends Command {
    TaskList taskList;
    String lineInput;
    Task event;

    public EventCmd(TaskList taskList, String lineInput) {
        super(taskList);
        this.lineInput = lineInput;
    }

    public void execute() {
        try { 
            this.event = Event.create(this.lineInput);
            taskList.add(this.event);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
    };
    
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n" + 
        Ui.indentString(this.event.toString(), 1) + "\n" + taskList.countTasks());
    };
}
