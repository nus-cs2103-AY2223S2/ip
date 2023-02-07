package command;

import task.TaskManager;

import util.DukeException;

/**
 * Executes delete task command.
 */
public class DeleteCommand extends Command {
    //private final TaskManager taskManager;
    private final int index;

    /**
     * Initialises task manager managing the list
     * and extracts list index from String input.
     *
     * @param taskManager
     * @param input
     */
    public DeleteCommand(String input) {
        //this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes task at index given by user.
     * <p>
     * String input is parsed to extract start and end
     * dates and timings of the event.
     *
     * @return
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            assert taskManager != null;
            taskManager.deleteTask(this.index);
            String str = "Okay! I have removed the task!";
            System.out.println("There are currently " + taskManager.getTaskArraySize() + " task(s) in the list!");
            return str;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
