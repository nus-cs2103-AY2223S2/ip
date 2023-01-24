package duke.commands;

import duke.Parser;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'unmark' command keyword.
 * Change status of a task to incomplete, at a specified index 
 * <p>
 * Command format: "unmark <list_index>"
 */
public class UnmarkCmd extends Command {
    private int index;
    private Task task;

    /**
     * Constructor method.
     * @param taskList The task list of the task to unmark
     * @param lineInput The command line input that the user entered
     */
    public UnmarkCmd(TaskList taskList, String line) {
        super(taskList, line);
        this.index = Parser.parseMarkUnmarkDelete(line);
    }

    // Changes the status of the specified task to incomplete.
    public void execute() {
        this.task = taskList.get(this.index).unmarkDone();
        uiReply();
    }

    // Acknowledge on UI that the status of the task has been changed to incomplete.
    public void uiReply() {
        String output = "Ok, I've marked this task as not done yet:";
        Ui.displayMsg(output + "\n" + Ui.indentString(this.task.toString(), 1));
    }
}
