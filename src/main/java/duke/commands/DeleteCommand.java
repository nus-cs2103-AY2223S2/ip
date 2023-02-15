package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents a Command that deletes tasks from TaskList.
 * */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand
     * @param index specifies task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from TaskList by index.
     * Prompts Ui to display deleted task followed by reminder of number of tasks in TaskList.
     * Prompts Storage to overwrite existing task storage file with new TaskList.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        try {
            Task deletedTask = tasks.delete(this.index);
            storage.writeToFile(tasks);
            return Formatter.formatDeleteTask(deletedTask, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            return "SEARCHING... ERROR! Task does not exist at specified index.";
        } catch (IOException e) {
            return "WRITING... ERROR! Unable to write to file. Please run Duke again.";
        }
    }
}
