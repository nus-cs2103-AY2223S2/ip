package boo.tasklist;

import java.util.ArrayList;

import boo.task.Task;

/**
 * Represents the list of {@code Tasks} that the user has entered into the chatbot.
 */
public class TaskList {
    /**
     * List that stores all the Task objects.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets number of tasks in the task list.
     *
     * @return the number of tasks in the task list.
     */
    public int getSizeOfTaskList() {
        return this.tasks.size();
    }

    /**
     * Gets the task at a specified index of the list.
     *
     * @param index The index in the list from which to retrieve the task.
     */
    public Task getTask(int index) {
        assert index >= 0 : "Invalid index";
        return this.tasks.get(index);
    }

    /**
     * Adds a {@code Task} into the task list.
     */
    public void addTask(Task taskToAdd) {
        this.tasks.add(taskToAdd);
    }

    /**
     * Deletes a {@code Task} from the task List, at a specified index.
     *
     * @param index Index of the task which is to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 : "Invalid index";
        tasks.remove(index);
    }

    /**
     * Gets all the user tasks that have been entered by the user thus far
     *
     * @return a string listing out all the available user tasks.
     */
    public String getUserTasks() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("There are currently no tasks in your list.");
            return sb.toString();
        }
        sb.append("Here are the tasks in your list:\n\n");
        int numberOfTasks = tasks.size();
        //Process each task in the storage
        for (int i = 0; i < numberOfTasks; i = i + 1) {
            String numbering = Integer.toString(i + 1) + ". ";
            String output = numbering + tasks.get(i).getStatusOfTaskInString() + "\n";
            sb.append(output);
        }
        return sb.toString();
    }
}
