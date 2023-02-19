package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Command for finding a Task.
 */
public class Find extends Command {

    private TaskList tasklist;
    private String searchKey;

    /**
     * Constructor for a Find command.
     * @param searchKey The keyword to search for.
     * @param tasklist The list to search in.
     */
    public Find(String searchKey, TaskList tasklist) {
        this.tasklist = tasklist;
        this.searchKey = searchKey;
    }

    @Override
    public String execute() {
        ArrayList<Task> currentTasks = tasklist.getWholeList();
        ArrayList<Pair<Integer, Task>> searchResults = new ArrayList<>();
        for (int i = 0; i < currentTasks.size(); i++) {
            Task curr = currentTasks.get(i);
            if (curr.getDescription().contains(searchKey)) {
                searchResults.add(new Pair<>(i + 1, curr));
            }
        }
        StringBuilder res = new StringBuilder();
        if (searchResults.size() != 0) {
            res.append("I've found these matching tasks:\n");
            for (Pair p : searchResults) {
                res.append(p.getKey() + ". " + p.getValue() + "\n");
            }
        } else {
            res.append("Sorry, I did not find any tasks matching your search keyword :-( .");
        }
        return res.toString();
    }
}
