package seedu.duke.tasklist;

import seedu.duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public boolean add(Task task) {
        return taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public TaskList search(String query) {
        TaskList result = new TaskList();
        for(Task task: taskList) {
            if (task.getDescription().contains(query)) {
                result.add(task);
            }
        }
        return result;
    }

    public Task get(int i) {
        return taskList.get(i);
    }
}
