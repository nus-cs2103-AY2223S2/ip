package duke.commands;

import duke.exception.DukeException;
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
    public String execute(TaskList taskList, UiText uiText, Storage storage) throws DukeException {
        TaskList matchingList = taskList.getMatchingTasks(keyword);
        return "These are the matching tasks in your list:\n"
                + matchingList;
    }
}
