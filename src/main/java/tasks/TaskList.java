package tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks to which operations can be performed.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Returns a TaskList with a new empty ArrayList of Task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns a TaskList with the given taskList as default tasks.
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds the given task to the task list.
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     * @param index
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Marks the task at the specified index as done.
     * @param index
     */
    public void markTask(int index) {
        this.tasks.get(index - 1).markAsDone();
    }

    /**
     * Marks the task at the specified index as undone.
     * @param index
     */
    public void unmarkTask(int index) {
        this.tasks.get(index - 1).markAsUndone();
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    /**
     * Returns a copy of the ArrayList of Task.
     * @return a copy of the ArrayList of Task.
     */
    public ArrayList<Task> getArrayListCopy() {
        return new ArrayList<>(this.tasks);
    }
}
