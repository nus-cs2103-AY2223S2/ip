package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents a Command that marks tasks in the TaskList as done.
 * */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand
     * @param index specifies task to be marked as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Finds the task in TaskList by user inputted index. Calls TaskList method to mark the task as done.
     * Marking an already done task as done is allowed and will not result in any error.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        try {
            Task currentTask = tasks.mark(this.index);
            storage.writeToFile(tasks);
            return Formatter.formatMarkTask(currentTask);
        } catch (IndexOutOfBoundsException e) {
            return "SEARCHING... ERROR! Task does not exist at specified index.";
        } catch (IOException e) {
            return "Unable to write to file. Please run Duke again.";
        }
    }
}
