package duke.data;
import java.util.ArrayList;

import duke.data.task.Task;

/**
 * Represents a task list.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructor for task list.
     *
     * @param tasks Tasks to create TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Constructor for empty task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Produces string representation of tasks for saving.
     *
     * @return String to be stored in Storage.
     */
    public String tasksToStr() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += get(i).storageStr();
            result += System.lineSeparator();
        }
        return result;
    }
}
