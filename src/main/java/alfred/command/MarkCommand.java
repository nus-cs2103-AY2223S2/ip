package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents a mark command where the user wishes to mark a task.
 */
public class MarkCommand extends Command {

    private int taskIndex;

    /**
     * Constructs a mark command with the given task index.
     * @param taskIndex The index of the task in the task list.
     */
    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        try {
            Task task = tasks.getTask(taskIndex);
            task.markAsDone();
            String output = "Well done! Good job "
                    + "for completing your task!\n";
            output += ui.getIndent();
            output += String.format("%s\n", task);
            return ui.getCommandMessage(output);
        } catch (NumberFormatException e) {
            throw new AlfredException("To mark, item you need to pass a valid integer!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new AlfredException(String.format("There are only %d pending tasks\n", tasks.getSize()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
