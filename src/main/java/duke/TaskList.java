package duke;
//
import duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> task;
    private int taskCounter;

    public TaskList() {
        task = new ArrayList<>();
        this.taskCounter = 0;
    }

    public void add(Task t) {
        task.add(t);
        taskCounter++;
    }
    public void remove(int index) {
        task.remove(index);
        taskCounter--;
    }
    public Task get(int index) {
        return task.get(index);
    }

    public int size() {
        return task.size();
    }

    public int getTaskCounter() {
        return this.taskCounter;
    }
}
