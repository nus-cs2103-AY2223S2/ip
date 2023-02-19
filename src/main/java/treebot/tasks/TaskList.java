package treebot.tasks;

import treebot.interfaces.ITaskList;

import java.util.ArrayList;

/**
 * Represents a list of tasks to which operations can be performed.
 */
public class TaskList implements ITaskList {

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

    public void addTask(Task task, int index) {
        this.tasks.add(index - 1, task);
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
    public Task deleteTask(int index) {
        Task deletedTask = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return deletedTask;
    }

    public Task deleteTask(Task task) {
        this.tasks.remove(task);
        return task;
    }

    /**
     * Marks the task at the specified index as done.
     * @param index
     */
    public Task markTask(int index) {

        this.tasks.get(index - 1).markAsDone();
        return this.tasks.get(index - 1);
    }

    /**
     * Marks the task at the specified index as undone.
     * @param index
     */
    public Task unmarkTask(int index) {
        this.tasks.get(index - 1).markAsUndone();
        return this.tasks.get(index - 1);
    }


    public TaskList find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                result.add(task);
            }
        }

        return new TaskList(result);
    }

    public String getPrintableTasks() {
        String printableTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            printableTasks += (i + 1 + "." + tasks.get(i)) + "\n";
        }

        return printableTasks;
    }

    /**
     * Returns a copy of the ArrayList of Task.
     * @return a copy of the ArrayList of Task.
     */
    public ArrayList<Task> getArrayListCopy() {
        return new ArrayList<>(this.tasks);
    }

    public int getSize() {
        return this.tasks.size();
    }


}
