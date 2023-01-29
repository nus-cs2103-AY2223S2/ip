package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> init) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(init);
    }

    public Task delete(int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        return deletedTask;
    }

    public void mark(int index) {
        taskList.get(index).markAsDone();
    }

    public void unmark(int index) {
        taskList.get(index).markAsUndone();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
