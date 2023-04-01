package peppa.commands;

import java.util.ArrayList;
import java.util.HashMap;

import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a find command that searches for tasks using one or more keywords.
 */
public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private String[] keywords;

    /**
     * Constructs a find command with the specified keyword.
     *
     * @param keywords Array of keywords that the user is searching for in the tasklist.
     */
    public FindCommand(String ...keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        HashMap<String, ArrayList<Task>> results = taskList.findTasks(keywords);
        return Ui.getMatchingTasks(results);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
