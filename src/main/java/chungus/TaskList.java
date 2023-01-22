package chungus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> _tasks) {
        tasks = new ArrayList<>(_tasks);
    }

    public void forEach(BiConsumer<Task, Integer> f) {
        for (int i = 0; i < tasks.size(); i++) {
            f.accept(tasks.get(i), i);
        }
    }

    public TaskList filter(Predicate<Task> f) {
        return new TaskList(tasks.stream().filter(f).collect(Collectors.toList()));
    }

    public String marshal() {
        return tasks.stream()
                .map(task -> task.marshal())
                .collect(Collectors.joining("\n"));
    }

    public int count() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int idx) {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(idx);
        }
    }

    public void setDone(int idx) {
        get(idx).setDone();
    }

    public void setNotDone(int idx) {
        get(idx).setNotDone();
    }

    public Task remove(int idx) {
        Task ret = get(idx);
        tasks.remove(idx);
        return ret;
    }

    @Override
    public boolean equals(Object _other) {
        if (!(_other instanceof TaskList)) {
            return false;
        }
        TaskList other = (TaskList) _other;
        if (this.tasks.size() != other.tasks.size()) {
            return false;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            if (!(this.tasks.get(i).equals(other.tasks.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
