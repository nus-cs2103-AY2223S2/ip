package duke.commands;

import duke.Parser;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCmd extends Command {
    Task task;

    public DeleteCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.lineInput = lineInput;
    } 

    public void execute() {
        int index = Parser.parseMarkUnmarkDelete(lineInput);
        this.task = this.taskList.removeTask(index);
        uiReply();
    }

    public void uiReply() {
        Ui.displayMsg("Noted. I've removed this task:\n" + 
        Ui.indentString(this.task.toString(), 1) + "\n" +
        Ui.numTaskToString(taskList.countTasks()));
    }

}
