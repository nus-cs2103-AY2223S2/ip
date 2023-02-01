package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.util.List;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    protected String keyword;

    /**
     * A constructor to intialize a find command.
     *
     * @param keyword The keyword to find the task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        try {
            List<Task> searched = taskList.search(keyword);
            TaskList tl = new TaskList(searched);
            return ui.printSearched(keyword, tl);
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        }
    }
}
