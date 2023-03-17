package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import gui.Gui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command for finding matches to user inputs.
 */
public class FindCommand extends Command {
    private final ArrayList<String> searchTerms;

    /**
     * Constructor for FindCommand;
     * @param searchTerms The terms to search.
     */
    public FindCommand(String... searchTerms) {
        this.searchTerms = new ArrayList<>();
        Collections.addAll(this.searchTerms, searchTerms);
    }

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        Predicate<Task> taskPredicate = task -> this.searchTerms.stream()
                .anyMatch(searchTerm -> task.getContent().contains(searchTerm));
        gui.say(taskList.listItems(taskPredicate));
    }
}
