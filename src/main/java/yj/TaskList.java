package yj;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    public int getNumberofTasks() {
        return tasks.size();
    }

    public void forEachTask(BiConsumer<Task, Integer> consumer) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            consumer.accept(task, i);
        }
    }

    public Task deleteTask(int i) throws IndexOutOfBoundsException{
        if (tasks.get(i - 1) != null) {
            Task task = removeTask(i - 1);
            return task;
        } else {
            throw  new IndexOutOfBoundsException();
        }
    }
}
