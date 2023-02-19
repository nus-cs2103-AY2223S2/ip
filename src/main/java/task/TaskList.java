package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * A data structure that saves a list of tasks.
 * It is directly inherited from ArrayList
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Default constructor.
     */
    public TaskList() {
        super();
    }

    /**
     * Construct an instance from an array list of tasks
     * @param tasks an array list of tasks
     *
     */
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Return the string representation of the task list
     *
     * @param isIndexed whether to add an index number at the beginning of each task
     *                   or not
     * @return the string representation of the task list
     */
    public String getTaskListString(boolean isIndexed) {
        String s = "";
        int count = 1;
        for (Task t : this) {
            s += (isIndexed ? (count + ". ") : "") + t;
            if (count < this.size()) {
                s += "\n";
            }
            count += 1;
        }
        return s;
    }

    /**
     * Get the task list that all tasks whose names contain at least one of the given strings
     * but do not contain all strings.
     * @param strings the strings to search for
     * @return a task list
     */
    public TaskList getOnlyPartiallyMatchedTaskNames(String... strings) {
        assert strings.length > 0 : "cannot search for empty string array";

        List<Task> filteredTasks = this
                .stream()
                .filter(t ->
                        Stream.of(strings).anyMatch(t::containString)
                                & (!Stream.of(strings).allMatch(t::containString))
                        )
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(filteredTasks));
    }

    /**
     * Get the task list that all tasks whose names contain all given strings
     * @param strings the strings to search for
     * @return a task list
     */
    public TaskList getFullyMatchedTaskNames(String... strings) {
        assert strings.length > 0 : "cannot search for empty string array";

        List<Task> filteredTasks = this
                .stream()
                .filter(t -> Stream.of(strings).allMatch(t::containString))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<>(filteredTasks));
    }
}
