package command;

import storage.TaskList;

public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for a mark task command.
     * @param index  index of task to be marked completed
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList) {
        taskList.markTask(this.index);
        return "Nice! I've marked this task as done:\n" + taskList.showTask(this.index);
    }
}
