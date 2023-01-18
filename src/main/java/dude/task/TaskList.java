package dude.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }

    public void addTask(Task task) {
        taskList.add(task);
        Task.addTaskCount();
    }

    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex - 1);
        Task.removeTaskCount();
    }

    public String toRaw() {
        StringBuilder input = new StringBuilder();
        for (Task task : taskList) {
            input.append(task.toRaw());
        }
        return input.toString();
    }

    @Override
    public String toString() {
        StringBuilder result;
        if (Task.count != 0) {
            result = new StringBuilder("\tHere are the tasks in your list: \n");
            for (int i = 0; i < Task.count; i++) {
                result.append("\t").append(i + 1).append(".").append(taskList.get(i).toString()).append("\n");
            }
        } else {
            result = new StringBuilder("\tEh... You currently got no task leh.\n");
        }
        return result.toString();
    }

}