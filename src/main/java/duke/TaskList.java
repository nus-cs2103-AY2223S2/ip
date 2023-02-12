package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list to contain tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Default class constructor, creates an empty list.
     */
    public TaskList() {
        super();
    }

    /**
     * Alternative class constructor, creates a filled task list with given input.
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.addAll(taskList);
    }
}
