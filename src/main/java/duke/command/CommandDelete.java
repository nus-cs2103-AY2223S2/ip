package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a delete command.
 */
public class CommandDelete extends Command {
    private final int index;

    /**
     * Returns a delete command.
     * @param command full unparsed command.
     * @param index index of task to be deleted.
     */
    public CommandDelete(String command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Deletes the specified task from the task list.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(index);
        String headerString = "Noted. I've removed this task:\n";
        String taskString = taskList.getTask(index).toString() + "\n";
        String len = (taskList.getLength() == 1 ? taskList.getLength() + " task" : taskList.getLength() + " tasks");
        String lengthString = "Now you have " + len + " in the list.\n";

        storage.writeArray(taskList);
        return headerString + taskString + lengthString;
    }
}
