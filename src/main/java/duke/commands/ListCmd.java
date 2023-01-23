package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;

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