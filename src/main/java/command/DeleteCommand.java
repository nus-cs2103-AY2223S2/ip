package command;

import task.TaskManager;

import util.DukeException;

public class DeleteCommand extends Command {
    private final TaskManager taskManager;
    private final int index;
    public DeleteCommand(TaskManager taskManager, String input) {
        this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    @Override
    public void executeCommand() throws DukeException {
        try {
            taskManager.deleteTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
