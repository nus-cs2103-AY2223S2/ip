package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to display tasks that match a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        TaskList matchingList = taskList.getMatchingTasks(keyword);
        if (matchingList.getSize() == 0) {
            return "There are no tasks that match '" + keyword + "'.";
        }
        return "These are the matching tasks in your list:\n"
                + matchingList;
    }
}
