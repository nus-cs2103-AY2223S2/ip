public class ListCmd extends Command {
    
    public ListCmd(TaskList taskList) {
        super(taskList);
    } 

    public void execute() {
        uiReply();
    }

    public void uiReply() {
        Ui.displayMsg("Here are the tasks in your list:\n" + taskList.outputList());
    }

}