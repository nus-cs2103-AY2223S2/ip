package command;

import dukeexeption.InvalidArgumentException;
import storage.TaskList;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for a delete task command.
     * @param index  index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        try {
            String taskDescription = taskList.showTask(this.index).toString();
            taskList.deleteTask(this.index);
            return "Noted. I've removed this task:\n  " + taskDescription +
                    "Now you have " + taskList.countTask() + " tasks in the list.";
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidArgumentException("Index " + (this.index + 1) + " is out of bound.");
        }
    }
}
