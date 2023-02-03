package command;

import java.util.function.Predicate;

import gui.Gui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command for finding matches to user inputs.
 */
public class FindCommand extends Command {
    private final String[] searchTerms;

    /**
     * Constructor for FindCommand;
     * @param searchTerms The terms to search.
     */
    public FindCommand(String... searchTerms) {
        this.searchTerms = searchTerms;
    }

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        Predicate<Task> taskPredicate = (task) -> {
            for (String searchTerm : this.searchTerms) {
                if (task.getContent().contains(searchTerm)) {
                    return true;
                }
            }
            return false;
        };
        gui.say(taskList.listItems(taskPredicate));
    }
}
