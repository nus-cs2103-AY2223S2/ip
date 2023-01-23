import java.util.ArrayList;


/**
 * A data structure that saves a list of tasks. It is directly inherited from ArrayList<Task>
 */
public class TaskList extends ArrayList<Task> {
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
}
