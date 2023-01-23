package task;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * A data structure that saves a list of tasks.
 * It is directly inherited from ArrayList<Task.Task>
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
     * @param tasks: an array list of tasks
     *
     */
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Return the string representation of the task list
     *
     * @param isIndexed: whether to add an index number at the beginning of each task
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
     * Get the task list that contains all tasks whose names contain the given string
     * @param string: the string to search for
     * @return a task list
     */
    public TaskList getTaskNameContains(String string) {
        List<Task> filteredTasks = this.stream().filter(t -> t.containString(string)).collect(Collectors.toList());
        return new TaskList(new ArrayList<>(filteredTasks));
    }
}
