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

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public boolean contains(Task task) {
        return this.tasks.contains(task);
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

    public TaskList makeTaskFinder(String searchWord) {
        TaskList taskFinder = new TaskList();
        for (Task task : tasks) {
            String[] taskName = task.getName().split(" ");
            for (String s : taskName) {
                if (s.equals(searchWord) && !taskFinder.contains(task)) {
                    taskFinder.addTask(task);
                }
            }
        }
        return taskFinder;
    }
}
