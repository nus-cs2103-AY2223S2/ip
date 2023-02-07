package duke.commands;

import duke.Parser;
import duke.Ui;
import duke.exceptions.ListIndexMissing;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'mark' command keyword.
 * Marks a task at a specified index as completed.
 * <p>
 * Command format: "mark &lt;list_index&gt;"
 */
public class MarkCmd extends Command {
    private int index;
    private Task task;

    /**
     * Constructor method.
     * @param taskList Task list of the task to mark
     * @param lineInput Command line input that the user entered
     */
    public MarkCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    // Marks the specified task as completed.
    public void execute() throws ListIndexMissing {
        this.index = Parser.parseMarkUnmarkDeleteIndex(lineInput);
        this.task = taskList.get(this.index).markDone();

        String output = "Nice! I've marked this task as done:";
        this.response = String.format("%s\n%s", output,
                Ui.indentString(this.task.toString(), 1));
    }
}
