package duke.storage;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Interface for storage of tasks.
 */
public interface Storage {
    List<Task> load();

    void save(List<Task> tasks) throws DukeException;
}
