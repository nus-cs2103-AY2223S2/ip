package duke.tasklist;
import duke.task.Task;

import java.util.ArrayList;

/**
 * ArchivedTaskList class.
 * Handles archived tasks.
 */
public class ArchivedTaskList extends TaskList {

    /**
     * Constructor for an archived task list.
     * @param arrayList The array list.
     */
    public ArchivedTaskList(ArrayList<Task> arrayList) {
        super(arrayList);
    }

    /**
     * Add a task that has just been deleted.
     * @param task Task that has been deleted.
     */
    public void addDeletedTask(Task task) {
        arrayList.add(task);
    }
}
