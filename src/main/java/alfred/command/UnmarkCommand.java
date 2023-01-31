package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;
    public UnmarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        try {
            Task task = tasks.getTask(taskIndex);
            task.unmarkTask();
            String output = "I have un-mark this task. Remember to complete "
                    + "your task on time!\n";
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
