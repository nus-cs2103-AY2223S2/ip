package duke.interfaces;

import java.util.List;

import duke.model.Task;

/**
 * Model is an interface for the data model in the task management application.
 * It provides access to the user's task list.
 */
public interface Model {
    /**
     * Gets the list of tasks stored in the model.
     *
     * @return the list of tasks
     */
    List<Task> getTasks();
}
