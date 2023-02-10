package command;

import dukeexeption.InvalidArgumentException;

import storage.TaskList;

/**
 * Command component that executes a delete command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for a delete task command.
     *
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        assert this.index >= 0;
        try {
            String taskDescription = taskList.showTask(this.index).toString();
            taskList.deleteTask(this.index);
            return "Noted. I've removed this task:\n  " + taskDescription + "\nNow you have " + taskList.countTask()
                    + " tasks in the list.";
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidArgumentException("Index " + (this.index + 1) + " is out of bound.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand that = (DeleteCommand) o;

        return index == that.index;
    }
}
