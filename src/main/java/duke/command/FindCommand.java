package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a FindCommand that implements the interface Command.
 */
public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        ArrayList<Task> matchedTasks = tasks.findMatchingTasks(keyword);
        return ui.displayMatchedTasks(matchedTasks);
    }
}
