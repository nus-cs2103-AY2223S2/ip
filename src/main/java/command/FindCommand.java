package command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import storage.TaskList;
import task.Task;

/**
 * Command component that executes a find command.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor for query command.
     *
     * @param query the query string
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String run(TaskList taskList) {
        ArrayList<Task> filteredList =
                taskList.indexTask().stream()
                        .filter((task) -> task.getTask().toLowerCase().contains(this.query.toLowerCase()))
                        .collect(Collectors.toCollection(ArrayList::new));
        if (filteredList.size() == 0) {
            return "There are no tasks matching this query.";
        } else {
            String res = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < filteredList.size(); i++) {
                res += String.format("%d.%s\n", i + 1, filteredList.get(i));
            }
            return res.trim();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindCommand)) {
            return false;
        }

        FindCommand that = (FindCommand) o;

        return query.equals(that.query);
    }
}
