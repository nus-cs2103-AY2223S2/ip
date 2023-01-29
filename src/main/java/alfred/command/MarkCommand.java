package alfred.command;

import alfred.task.TaskList;
import alfred.task.Task;
import alfred.ui.Ui;
import alfred.storage.Storage;
import alfred.exceptions.AlfredException;

public class MarkCommand extends Command {

    private int taskIndex;
    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        try {
            Task task = tasks.getTask(taskIndex);
            task.markAsDone();
            String output = "Well done! Good job " +
                    "for completing your task!\n";
            output += String.format("      %s\n", task);
            ui.displayCommand(output);
        } catch (NumberFormatException e) {
            throw new AlfredException("To mark, item you need to pass a valid integer!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new AlfredException(String.format("There are only %d pending tasks\n", tasks.getSize()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
