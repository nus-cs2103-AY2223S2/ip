package duke;

import duke.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    protected int items;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.items = tasks.size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getItems() {
        return this.items;
    }

    public String addTask(Task task) {
        tasks.add(task);
        items++;
        return "Got it. I've added this task:\n"
                + String.format(" [%s][ ] %s\n Now you have %s tasks in the list.", task.getTaskType(), task, items);
    }

    public String deleteTask(int taskNumber) {
        Task removedTask = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        items--;
        return "Noted. I've removed this task:\n"
                + String.format(" [%s][%s] %s\n Now you have %s tasks in the list.",
                removedTask.getTaskType(), removedTask.getStatusIcon() , removedTask, items);
    }

    public String printTaskList() {
        StringBuilder tasklist = new StringBuilder();
        tasklist.append("Here are the tasks in your list:" + "\n");

        for(int i = 0; i < items; i++) {
            String taskToAdd = String.format("%s.[%s][%s] %s\n", i + 1, tasks.get(i).getTaskType(),
                    tasks.get(i).getStatusIcon(), tasks.get(i).toString());
            tasklist.append(taskToAdd);
        }

        return tasklist.toString();
    }
}
