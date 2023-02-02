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

    /**
     * Returns the task at the given index from the task list.
     * @param index index of task in the task list
     * @return the task at the index given
     */
    public Task getTaskByIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds the given task into the task list.
     * @param task task to be added into the task list
     */
    public void addTask(Task task){
        this.tasks.add(task);
    }

    /**
     * Deletes and returns the task deleted from the task list.
     * @param indexOfTask index of task to be deleted
     * @return the task deleted
     */
    public Task deleteTask(int indexOfTask) {
        return this.tasks.remove(indexOfTask - 1);
    }

    /**
     * Marks the task given by its index as done.
     * @param indexOfTask index of the task to mark as done
     * @return the task after marking it as done
     */
    public Task markTaskAsDone(int indexOfTask) {
        Task toMarkDone = this.tasks.get(indexOfTask);
        toMarkDone.markDone();
        return toMarkDone;
    }

    /**
     * Marks the task given by its index as undone.
     * @param indexOfTask index of the task to mark as undone
     * @return the task after marking it as undone
     */
    public Task markTaskAsUndone(int indexOfTask) {
        Task toMarkUndone = this.tasks.get(indexOfTask);
        toMarkUndone.markUndone();
        return toMarkUndone;
    }

    /**
     * Retrieves the size of the task list.
     * @return size of task list
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks in user output format and informs
     * user if no task is present in the task list.
     */
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
