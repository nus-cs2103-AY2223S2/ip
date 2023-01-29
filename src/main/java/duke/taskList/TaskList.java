package taskList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dukeexception.StorageException;
import tasks.Task;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(TaskList tasks) {
        super.addAll(tasks);
    }

    public List<Task> getList() {
        return Collections.unmodifiableList(this);
    }

    public boolean add(Task item) {
        boolean added = super.add(item);

        return added;
    }

    public Task get(int id) {
        return super.get(id);
    }

    public Task delete(int id) throws StorageException {
        if (id >= this.size()) {
            throw new StorageException("☹ OOPS!!! delete index does not exist");
        }

        Task task = super.remove(id);

        return task;
    }

    public int size() {
        return super.size();
    }
}