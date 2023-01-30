package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A command that stores the command to mark a task as done. The action of adding the task can be carried out when
 * called.
 */
public class MarkCommand extends Command {
    /**
     * The string representation of the index to be marked as done.
     */
    private final String INDEX_STRING;

    /**
     * Constructor for a command to mark a task as done.
     *
     * @param commandString The mark command in string representation
     * @param INDEX_STRING  The string representation of the index to be marked as done
     */
    public MarkCommand(String commandString, String INDEX_STRING) {
        super(Commands.MARK, commandString);
        this.INDEX_STRING = INDEX_STRING;
    }

    /**
     * Marks the task as done in the task list with the given index.
     * Checks if the index in string representation is valid. If so, mark the appropriate task as done. Otherwise,
     * throw an exception that states the issue with the string representation of the index.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTasks();
        int index = this.isValidIndex(this.INDEX_STRING, t);

        t.get(index).markTask();

        String msgHeader = "I've marked this task as done:";

        ui.showMsg(msgHeader);
        ui.showMsg(t.get(index).toString());
    }
}
