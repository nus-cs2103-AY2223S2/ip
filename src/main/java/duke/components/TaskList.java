package duke.components;

import duke.tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index-1);
    }

    public void deleteTask(int indexOfTask) {
        tasks.remove(indexOfTask - 1);
    }

    public void mark(int markIndex) {
        Task currTask = tasks.get(markIndex-1);
        currTask.markDone();
    }

    public Task getTask(int markIndex) {
        return tasks.get(markIndex-1);
    }

    public void unmark(int unmarkIndex) {
        Task currTask = tasks.get(unmarkIndex-1);
        currTask.unmark();
    }
}
