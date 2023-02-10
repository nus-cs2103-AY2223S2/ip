package duke.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import duke.entities.Task;
import duke.entities.managers.CacheManager;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.views.UI;


/**
 * A stub implementation of TaskList.
 */
public class TestCacheManagerStub extends CacheManager {
    /** In memory store **/
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Initializes a TaskList with preloaded data.
     *
     * @param storage storage class for writing to hard disk.
     */
    public TestCacheManagerStub(Storage storage) throws DukeException {
        super(storage);
    }

    /**
     * A generic filter function which parses through the TaskList and return tasks that match.
     *
     * @param predicate A boolean function.
     */
    public String filter(Predicate<? super Task> predicate, String emptyMsg) {
        return "";
    }

    private String filter(Predicate<? super Task> predicate, String emptyMsg, boolean index) {
        return "";
    }

    /**
     * Adds a given task into the TaskList.
     *
     * @param task The task to be added.
     * @param print Boolean value indicating if console messages should be printed.
     */
    public String addTask(Task task, boolean print) {
        taskList.add(task);
        if (print) {
            return "Successfully added task to memory.";
        }
        return UI.newLine();
    }

    /**
     * Adds a task into the taskList.
     *
     * @param task The task to be added.
     * @throws DukeException A duke specific exception thrown due to adding error.
     */
    public String addTask(Task task) throws DukeException {
        taskList.add(task);
        return "Got it. I've added this task:"
                + UI.indentMessage(String.valueOf(task))
                + UI.newLine() + "Now you have " + taskList.size() + " tasks in the list.";
    }

    private boolean isValidKey(Integer key) {
        return (key <= taskList.size() && key > 0);
    }

    /**
     * Gets the task from the task list.
     *
     * @param key The task of choice.
     * @return The specified task.
     * @throws DukeException An error indicating there was an error in retrieving the specified task.
     */
    public Task getTask(Integer key) throws DukeException {
        if (!isValidKey(key)) {
            throw new DukeException("This task don't exists! Please select one from the list.");
        }
        // accounts for 0-based indexing
        return taskList.get(0);
    }
}
