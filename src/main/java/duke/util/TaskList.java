package duke.util;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>(100);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public String markTask(int index) {
        return taskList.get(index).mark();
    }

    public String unmarkTask(int index) {
        return taskList.get(index).unmark();
    }
}
