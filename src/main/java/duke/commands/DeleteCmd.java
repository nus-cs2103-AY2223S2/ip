package duke.commands;

import duke.Duke;
import duke.Parser;
import duke.Ui;
import duke.exceptions.CommandExecutionError;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'delete' keyword.
 * Deletes a task from the task list according to a specified index.
 * <p>
 * Command format: "delete &lt;list_index&gt;"
 */
public class DeleteCmd extends Command {
    private Task task;

    /**
     * Constructor method.
     * @param taskList Task list to remove the task from
     * @param lineInput Command line input that the user entered
     */
    public DeleteCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Deletes the specified task from the task list. */
    public void execute(Duke duke) throws CommandExecutionError {
        int index = Parser.parseMarkUnmarkDeleteIndex(lineInput);
        this.task = this.taskList.removeTask(index);
        
        duke.sendResponse("Noted. I've removed this task:\n"
                + Ui.indentString(this.task.toString(), 1) + "\n"
                + Ui.numTaskToString(taskList.countTasks()));
    }
}
