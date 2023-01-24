package duke.commands;

import duke.Parser;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'mark' command keyword.
 * Marks a task at a specified index as completed.
 * <p>
 * Command format: "mark <list_index>"
 */
public class MarkCmd extends Command {
    private int index;
    private Task task;

    /**
     * Constructor method.
     * @param taskList The task list of the task to mark
     * @param lineInput The command line input that the user entered
     */
    public MarkCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
        this.index = Parser.parseMarkUnmarkDelete(lineInput);
    }

    // Marks the specified task as completed.
    public void execute() {
        this.task = taskList.get(this.index).markDone();
        uiReply();
    }

    // Acknowledge on UI that the task has been marked as complete.
    public void uiReply() {
        String output = "Nice! I've marked this task as done:";
        Ui.displayMsg(output + "\n" + Ui.indentString(this.task.toString(), 1));
    }
}
