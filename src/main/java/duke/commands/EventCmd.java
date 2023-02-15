package duke.commands;

import duke.Ui;
import duke.exceptions.CommandExecutionError;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * Command class for 'event' command keyword.
 * Creates a new Event task and adds it to the task list.
 * Command input must be accompanied by a '/from' & '/to' keyword to indicate the start/end dates of the task.
 * <p>
 * Command format: "event &lt;task_name&gt; /from &lt;start_date&gt; /by &lt;end_date&gt;"
 */
public class EventCmd extends Command {
    private Task event;

    /**
     * Constructor method.
     *
     * @param taskList Task list to add the Event task to
     * @param lineInput Command line input that the user entered
     */
    public EventCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Adds the Event task to the list. */
    public String execute() throws CommandExecutionError {
        this.event = Event.create(this.lineInput);
        taskList.add(this.event);

        return String.format("A new event! I hope it's at night:\n%s\n%s",
                Ui.indentString(this.event.toString(), 1),
                Ui.numTaskToString(taskList.countTasks()));
    }
}
