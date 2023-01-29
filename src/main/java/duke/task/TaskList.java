package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delete(int taskNum) {
        tasks.remove(taskNum);
    }

    public int getLength() {
        return tasks.size();
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }
}
