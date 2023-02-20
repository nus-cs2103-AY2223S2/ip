package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a mark command.
 */
public class CommandMark extends Command {
    private final int index;
    private final boolean isMarked;

    /**
     * Returns a mark/unmark command.
     * @param command full unparsed command.
     * @param index index of task to be marked/unmarked.
     * @param mark whether to mark or unmark.
     */
    public CommandMark(String command, int index, boolean mark) {
        super(command);
        this.index = index;
        this.isMarked = mark;
    }

    /**
     * Marks the specified task in the task list.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index, isMarked);
        String markedString = (isMarked)
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        String taskString = (taskList.getTask(index).toString() + "\n");
        storage.writeArray(taskList);
        return markedString + taskString;
    }

    /**
     * Returns an inverse command to mark/unmark a task.
     * @param taskList contains the task list.
     * @return inverse task command.
     */
    @Override
    public Command inverseCommand(TaskList taskList) {
        return new CommandMark(this.getFullCommand(), index, !isMarked);
    }
}
