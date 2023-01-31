package peppa.commands;

import java.util.ArrayList;

import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a find command that searches for tasks using a keyword.
 */
public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Constructs a find command with the specified keyword.
     *
     * @param keyword The keyword that the user is searching for in the tasklist.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        ArrayList<Task> results = taskList.findTasks(keyword);
        if (results.size() == 0) {
            throw new PeppaException("Boink! Peppa could not find any tasks with the specified keyword. "
                    + "Please try again.\n");
        } else {
            return Ui.getMatchingTasks(results);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
