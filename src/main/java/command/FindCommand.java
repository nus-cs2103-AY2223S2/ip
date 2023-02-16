package command;

import java.util.ArrayList;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;


/**
 * Find all tasks with description that contains the keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs FindCommand.
     *
     * @param keyword Keywords to search of specific tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Retrieves all task that has description containing the keyword, outputs to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if index is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> t = taskList.findTask(keyword);
        ui.outputFoundTask(t);
    }
}



