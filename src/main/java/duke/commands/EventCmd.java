package duke.commands;

import duke.Ui;
import duke.exceptions.TaskInitError;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * Command class for 'event' command keyword.
 * Creates a new Event task and adds it to the task list.
 * Command input must be accompanied by a '/from' & '/to' keyword to indicate the start/end dates of the task.
 * <p>
 * Command format: "event <task_name> /from <start_date> /by <end_date>"
 */
public class EventCmd extends Command {
    private Task event;

    /**
     * Constructor method
     * @param taskList Task list to add the Event task to
     * @param lineInput Command line input that the user entered
     */
    public EventCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.lineInput = lineInput;
    }

    // Adds the Event task to the list
    public void execute() {
        try { 
            this.event = Event.create(this.lineInput);
            taskList.add(this.event);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
        uiReply();
    };
    
    // Acknowlege on UI that the Deadline task has been added.
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n" + 
        Ui.indentString(this.event.toString(), 1) + "\n" +
        Ui.numTaskToString(taskList.countTasks()));
    };
}
