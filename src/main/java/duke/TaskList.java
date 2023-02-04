package duke;

import java.util.ArrayList;
import java.util.List;


public class TaskList extends ArrayList<Task> {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int taskID) {
        return taskList.get(--taskID);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskID) {
        Task task = taskList.remove(--taskID);
        return task;
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public String toString() {
        String output = "";
        if (!taskList.isEmpty()) {
            output = "Here are the tasks in your list: \n";
            int count = 1;
            for (Task t : taskList) {
                output += count++ + "." + t.toString() + "\n";
            }
        } else {
            output = "You have no tasks";
        }
        return output;
    }
}
