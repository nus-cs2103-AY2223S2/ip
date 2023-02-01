package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.util.ArrayList;

/**
 * Find task that matches keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs FindCommand.
     *
     * @param keyword Keywords to find matching task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Outputs all task that matches keyword to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if index is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> t = taskList.findTask(keyword);
        ui.outputFindTask(t);
    }
}



