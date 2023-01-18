package dude;

import dude.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
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

    public Task deleteTask(int taskIndex) {
        Task task = taskList.remove(taskIndex - 1);
        Task.removeTaskCount();
        return task;
    }

    public String toRaw() {
        String input = "";
        for (Task task : taskList) {
            input += task.toRaw();
        }
        return input;
    }

    @Override
    public String toString() {
        String result = "";
        if (Task.count != 0) {
            result = "\tHere are the tasks in your list: \n";
            for (int i = 0; i < Task.count; i++) {
                result += "\t" + (i + 1) + "." + taskList.get(i).toString() + "\n";
            }
        } else {
            result = "\tEh... You currently got no task leh.\n";
        }
        return result;
    }

}