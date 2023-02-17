package duke.commands;

import duke.exceptions.ListIndexMissing;
import duke.parsing.MessageFormat;
import duke.parsing.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'mark' command keyword.
 * Marks a task at a specified index as completed.
 * <p>
 * Command format: "mark &lt;list_index&gt;"
 */
public class MarkCmd extends Command {

    /**
     * Constructor method.
     *
     * @param taskList task list of the task to mark
     * @param lineInput command line input that the user entered
     */
    public MarkCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Marks the specified task as completed. */
    public String execute() throws ListIndexMissing {
        int index = Parser.parseMarkUnmarkDeleteIndex(lineInput);
        Task taskToMark = taskList.get(index).markDone();
        assert taskToMark.getIsDone() == true;
        String output = "Wonderful. You've completed the task:";
        return String.format("%s\n%s", output,
                MessageFormat.indentString(taskToMark.toString(), 1));
    }
}
