package duke.commands;

import duke.Ui;
import duke.exceptions.TaskInitError;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'deadline' command keyword.
 * Creates a new Deadline task and adds it to the task list.
 * Command input must be accompanied by a '/by' keyword to indicate the due date of the task.
 * <p>
 * Command format: "deadline <task_name> /by <due_date>"
 */
public class DeadlineCmd extends Command {
    private Task deadline;

    /**
     * Constructor method.
     * @param taskList Task list to add the Deadline task to
     * @param lineInput Command line input that the user entered
     */
    public DeadlineCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }


    // Adds the Deadline task to the task list.
    public void execute() {
        try { 
            this.deadline = Deadline.create(this.lineInput);
            taskList.add(this.deadline);
        } catch (TaskInitError e) {
            Ui.displayMsg("OOPS!!! " + e.getMessage());
        } 
        uiReply();
    };
    
    // Acknowlege on UI that the Deadline task has been added.
    public void uiReply() {
        Ui.displayMsg("Got it. I've added this task:\n"
                + Ui.indentString(this.deadline.toString(), 1)
                + "\n" + Ui.numTaskToString(taskList.countTasks()));
    };
}
