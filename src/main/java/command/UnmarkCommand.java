package command;

import storage.TaskList;

public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for a mark task command.
     * @param index  index of task to be marked completed
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList) {
        taskList.unmarkTask(this.index);
        return "OK, I've marked this task as not done yet:\n" + taskList.showTask(this.index);
    }
}
