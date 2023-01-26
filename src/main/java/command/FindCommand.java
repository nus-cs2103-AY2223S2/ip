package command;

import exception.DukeException;
import exception.InvalidDateFormatException;
import sys.Storage;
import sys.Ui;
import task.TaskList;

/**
 * Represents a command used to find tasks by searching for a keyword.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructor for FindCommand
     * @param input Input to parse.
     */
    public FindCommand(String input) {
        super("find .*");
        this.input = input;
    }

    /**
     * Executes the find command to display all tasks containing a keyword.
     *
     * @param tl The task list to search through.
     * @param ui The UI used to interact with the user.
     * @param storage The storage used to persist the tasks.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        // Handle occurs
        String word = this.input.substring(5);

        // Print tasks that contain keyword
        tl.findTasksWithWord(word);
    }
}
