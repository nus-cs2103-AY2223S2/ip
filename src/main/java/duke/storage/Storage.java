package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

public interface Storage {
    List<Task> load();

    void save(List<Task> tasks) throws DukeException;
}
