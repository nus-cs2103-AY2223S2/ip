package duke.task;

import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task markAsDone(int taskNum) {
        this.tasks.get(taskNum).markAsDone();
        return this.tasks.get(taskNum);
    }

    public Task markAsUndone(int taskNum) {
        this.tasks.get(taskNum).markAsUndone();
        return this.tasks.get(taskNum);
    }

    public Task delete(int taskNum) {
        Task deletedTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        return deletedTask;
    }

    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task get(int taskNum) {
        return this.tasks.get(taskNum);
    }

    public int size() {
        return this.tasks.size();
    }

    public TaskList find(String userInput) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            String currentTask = this.tasks.get(i).getDescription();
            if (currentTask.contains(userInput)) {
                filteredTasks.add(this.tasks.get(i));
            }
        }
        return filteredTasks;
    }
}