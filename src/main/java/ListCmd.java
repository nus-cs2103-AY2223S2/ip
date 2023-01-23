public class ListCmd extends Command {
    
    public ListCmd(TaskList taskList) {
        super(taskList);
    } 

    public void execute() {
        uiReply();
    }

    public void uiReply() {
        Ui.displayMsg(taskList.outputList());
    }

}