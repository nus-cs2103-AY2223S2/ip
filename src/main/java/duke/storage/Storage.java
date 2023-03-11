package duke.storage;

import duke.enums.TaskTypes;
import duke.task.Task;

import java.util.ArrayList;

public interface Storage {
    ArrayList<Task> load();
    void addTask(Task t, TaskTypes type);
    void deleteTask(int index);
    void mark(int index);
    void unmark(int index);
}
