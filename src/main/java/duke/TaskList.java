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

    public String findKeyword(String keyword) {
        StringBuilder tasksWithKeyword = new StringBuilder();
        tasksWithKeyword.append("Here are the tasks in your list containing the keyword " + keyword + ":\n");
        int keywordCounter = 1;

        for (Task taskToCheck : tasks) {
            if (taskToCheck.toString().contains(keyword)) {
                String taskWithKeyword = String.format("%s.[%s][%s] %s\n", keywordCounter, taskToCheck.getTaskType(),
                        taskToCheck.getStatusIcon(),taskToCheck.toString());
                tasksWithKeyword.append(taskWithKeyword);
                keywordCounter++;
            }
        }

        return tasksWithKeyword.toString();

    }

    public String printTaskList() {
        StringBuilder tasklist = new StringBuilder();
        tasklist.append("Here are the tasks in your list:" + "\n");
        for(int i = 0; i < items; i++) {
            tasklist.append(i + 1).append(".").append("[").append(tasks.get(i).getTaskType()).append("][")
                    .append(tasks.get(i).getStatusIcon()).append("] ").append(tasks.get(i).toString()).append("\n");
        }
        return tasklist.toString();
    }
}
