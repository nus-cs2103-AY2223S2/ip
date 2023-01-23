package duke.commands;

import duke.Ui;
import duke.exceptions.TaskInitError;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class EventCmd extends Command {
    Task event;

    public EventCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.lineInput = lineInput;
    }

    public void execute() {
        try { 
            this.event = Event.create(this.lineInput);
            taskList.add(this.event);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
        uiReply();
    };
    
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n" + 
        Ui.indentString(this.event.toString(), 1) + "\n" +
        Ui.numTaskToString(taskList.countTasks()));
    };
}
