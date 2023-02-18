package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

/**
 * A class representing a command to find tasks with a certain keyword in the task list.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructor that creates a FindCommand with the specified keyword.
     * @param keyword the keyword to be searched for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks with the specified keyword in the task list.
     * @param ui the user interface to display the result of the command
     * @param list the task list to search for tasks
     * @param storage the storage for saving the task list
     * @return the response to the command
     */
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        Tasklist matchedList = list.find(this.keyword);
        return ui.getMatchedListReply(matchedList);
    }
}
