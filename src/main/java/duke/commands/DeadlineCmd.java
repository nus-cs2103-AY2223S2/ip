package duke.commands;

import duke.Ui;
import duke.exceptions.TaskInitError;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeadlineCmd extends Command {
    Task deadline;

    public DeadlineCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    public void execute() {
        try { 
            this.deadline = Deadline.create(this.lineInput);
            taskList.add(this.deadline);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
        uiReply();
    };
    
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n"
                + Ui.indentString(this.deadline.toString(), 1)
                + "\n" + Ui.numTaskToString(taskList.countTasks()));
    };
}
