public class ListCmd extends Command {
    
    public ListCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    } 

    public void execute() {
        uiReply();
    }

    public void uiReply() {
        Ui.displayMsg("Here are the tasks in your list:\n" + taskList.outputList());
    }

}