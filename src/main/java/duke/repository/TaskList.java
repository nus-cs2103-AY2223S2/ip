package duke.repository;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }
    public void addTask(Task oneTask) {
        taskList.add(oneTask);
    }

    public void deleteTask(int taskId) {
        taskList.remove(taskId);
    }

    public void markTask(int taskId) {
        Task oneTask = taskList.get(taskId);
        oneTask.markTask();
    }

    public void unmarkTask(int taskId) {
        Task oneTask = taskList.get(taskId);
        oneTask.unmarkTask();
    }

    public int numTasks() {
        return taskList.size();
    }

    public String getTaskList() {
        String listString = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task oneTask = taskList.get(i);
            listString += "\n" + (i + 1) + ". " + oneTask.toString();
        }
        return listString;
    }
}
