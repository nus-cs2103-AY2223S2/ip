package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents a Command that marks tasks in the TaskList as undone.
 * */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a UnmarkCommand
     * @param index specifies task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Finds the task in TaskList by user inputted index. Calls TaskList method to mark the task as undone.
     * Marking an undone task as undone is allowed and will not result in any error.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        try {
            Task currentTask = tasks.unmark(this.index);
            storage.writeToFile(tasks);
            return Formatter.formatUnmarkTask(currentTask);
        } catch (IndexOutOfBoundsException e) {
            return "SEARCHING... ERROR! Task does not exist at specified index.";
        } catch (IOException e) {
            return "WRITING... ERROR! Unable to write to file. Please run Duke again.";
        }
    }
}
