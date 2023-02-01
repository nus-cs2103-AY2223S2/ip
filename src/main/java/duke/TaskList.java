package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTaskByIndex(int index) {
        return this.tasks.get(index);
    }
    public void addTask(Task task){
        this.tasks.add(task);
    }

    public Task deleteTask(int indexOfTask) {
        return this.tasks.remove(indexOfTask - 1);
    }

    public Task markTaskAsDone(int indexOfTask) {
        Task toMarkDone = this.tasks.get(indexOfTask);
        toMarkDone.markDone();
        return toMarkDone;
    }

    public Task markTaskAsUndone(int indexOfTask) {
        Task toMarkUndone = this.tasks.get(indexOfTask);
        toMarkUndone.markUndone();
        return toMarkUndone;
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public void printTaskList() {
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, currTask.description());
        }
        if (tasks.isEmpty()) {
            System.out.println("No tasks in task list.");
        }
    }
}
