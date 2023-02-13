package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents a delete command that is given by the user to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * Constructs a delete command with the given command.
     * @param taskIndex The full command to determine what is to be added.
     */
    public DeleteCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            String output = "Noted. I've removed this task. Remember to clear your remaining tasks!\n";
            output += String.format("      %s\n", task);
            return ui.getCommandMessage(output);
        } catch (NumberFormatException e) {
            throw new AlfredException("To delete, item you need to pass a valid integer!\n");
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
