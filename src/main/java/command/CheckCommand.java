package command;

import task.TaskManager;

import util.DukeException;
import util.Parser;

public class CheckCommand extends Command {
    private final TaskManager taskManager;
    private final int index;
    public CheckCommand(TaskManager taskManager, String input) {
        this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    @Override
    public void executeCommand() {
        //System.out.println(this.index);
        try {
            taskManager.checkTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item does not exist in list! Please check your list again.");
        }
    }
}
