package kude.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Stream<Task> list() {
        return tasks.stream();
    }

    public boolean delete(Task task) {
        return tasks.remove(task);
    }

    public Optional<Task> get(int index) {
        if (index < 0 || index >= tasks.size()) {
            return Optional.empty();
        } else {
            return Optional.of(tasks.get(index));
        }
    }
}
