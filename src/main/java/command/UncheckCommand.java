package command;

import task.TaskManager;

import util.Parser;
public class UncheckCommand extends Command {
    private final TaskManager taskManager;
    private final int index;

    public UncheckCommand(TaskManager taskManager, String input) {
        this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }
    @Override
    public void executeCommand() {
        try {
            taskManager.uncheckTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item does not exist in list! Please check your list again.");
        }
    }
}
