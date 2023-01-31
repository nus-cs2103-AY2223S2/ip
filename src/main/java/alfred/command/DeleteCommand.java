package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.Ui;

public class DeleteCommand extends Command {

    private int taskIndex;
    public DeleteCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            String output = "Noted. I've removed this task. Remember to clear your "
                    + "remaining tasks!\n";
            output += String.format("      %s\n", task);
            ui.displayCommand(output);
        } catch (NumberFormatException e) {
            throw new AlfredException("To delete, item you need to pass a valid integer!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new AlfredException(String.format("There are only %d pending tasks\n", tasks.getSize()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
