package duke.commands;

import duke.Ui;
import duke.exceptions.ListIndexMissing;
import duke.parsing.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'unmark' command keyword.
 * Change status of a task to incomplete, at a specified index
 * <p>
 * Command format: "unmark &lt;list_index&gt;"
 */
public class UnmarkCmd extends Command {

    /**
     * Constructor method.
     * 
     * @param taskList Task list of the task to unmark
     * @param lineInput Command line input that the user entered
     */
    public UnmarkCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);

    }

    /** Changes the status of the specified task to incomplete. */
    public String execute() throws ListIndexMissing {
        int index = Parser.parseMarkUnmarkDeleteIndex(lineInput);
        Task taskToUnmark = taskList.get(index).markDone();
        assert taskToUnmark.getIsDone() == false;
        String output = "Ok, I've marked this task as not done yet:";
        return String.format("%s\n%s", output,
                Ui.indentString(taskToUnmark.toString(), 1));
    }
}
