package duke.commands;

import duke.Parser;
import duke.Ui;
import duke.exceptions.ListIndexMissing;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'unmark' command keyword.
 * Change status of a task to incomplete, at a specified index
 * <p>
 * Command format: "unmark &lt;list_index&gt;"
 */
public class UnmarkCmd extends Command {
    private int index;
    private Task task;

    /**
     * Constructor method.
     * @param taskList Task list of the task to unmark
     * @param lineInput Command line input that the user entered
     */
    public UnmarkCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);

    }

    // Changes the status of the specified task to incomplete.
    public void execute() throws ListIndexMissing {
        this.index = Parser.parseMarkUnmarkDeleteIndex(this.lineInput);
        this.task = taskList.get(this.index).unmarkDone();

        String output = "Ok, I've marked this task as not done yet:";
        this.response = String.format("%s\n%s", output,
                Ui.indentString(this.task.toString(), 1));
    }
}
