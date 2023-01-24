package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.task.Task;

public class TaskList implements Serializable {

    private static final long serialVersionUID = 1094392670804693665L;

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public String countTaskAsString() {
        int n = tasks.size();
        return String.format("%d task%s", n, n < 2 ? "" : "s");
    }

    private String listTasksFrom(Stream<Task> stream) {
        return stream.map(new Function<Task, String>() {
            int index = 1;

            @Override
            public String apply(Task task) {
                String out = String.format("%d.%s", index, task);
                index++;
                return out;
            }
        }).collect(Collectors.joining("\n"));
    }

    public String listAllTasks() {
        return listTasksFrom(tasks.stream());
    }

    @Override
    public String toString() {
        return String.format("TaskList: %s", tasks);
    }

}
