package duke.commands;

import java.util.Comparator;

import duke.tasks.Task;
import duke.tasks.taskcomparator.DateComparator;
import duke.tasks.taskcomparator.DescriptionComparator;
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
        return tasks.sort(c);
    }

    private Comparator<Task> getComparator(String key) {
        if (key.equals("name")) {
            return new DescriptionComparator();
        } else if (key.equals("date")) {
            return new DateComparator();
        } else {
            // should not reach here
            assert false;
            return new DescriptionComparator();
        }
    }
}
