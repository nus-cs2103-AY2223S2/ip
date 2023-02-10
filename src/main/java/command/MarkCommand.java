package command;

import dukeexeption.InvalidArgumentException;

import storage.TaskList;

/**
 * Command component that executes a mark command.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for a mark task command.
     *
     * @param index index of task to be marked completed
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        assert this.index >= 0;
        try {
            taskList.markTask(this.index);
            return "Nice! I've marked this task as done:\n" + taskList.showTask(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidArgumentException("Index " + (this.index + 1) + " is out of bound.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }

        MarkCommand that = (MarkCommand) o;

        return index == that.index;
    }
}
