package duke.commands;

import java.util.Comparator;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * SortCommand sorts the task list by a given key.
 */
public class SortCommand extends Command {
    private final String key;

    /**
     * Public constructor for Deadline command.
     * @param tokens Tokenised user inputs from user.
     */
    public SortCommand(String sortKey) {
        this.key = sortKey;
    }

    /**
     * Method to sort a task by key.
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        Comparator<Task> c = getComparator(this.key);
        tasks.sort(c);
        return dukeIo.showAll();
    }

    private Comparator<Task> getComparator(String key) {
        if (key == "name") {
            return (o1, o2) -> o1.getDescription().compareTo(o2.getDescription());
        }
        return (o1, o2) -> o1.getDescription().compareTo(o2.getDescription());
    }
}
