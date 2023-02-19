package duke.storage;

import duke.task.Task;

import java.util.List;

public class StubStorage implements Storage {
    private List<Task> tasks;

    public StubStorage(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<Task> load() {
        return tasks;
    }

    @Override
    public void save(List<Task> tasks) { }
}
