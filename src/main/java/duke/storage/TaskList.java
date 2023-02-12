package duke.storage;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Class for TaskList
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get list of tasks
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Delete a task at a specified index of TaskList
     * @param index Index of task to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Get a task from a TaskList at a specified index
     * @param index Index of task to get
     * @return Task from specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Get size of TaskList
     * @return Size of TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Add a task to a TaskList and echoes to user that task has been added as well as save all tasks in TaskList to
     * task log file
     * @param task Task to be added
     */
    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
        Storage.saveTasksToTaskLog(this);
    }

    /**
     * Extracts a list of tasks containing keyword
     * @param keyword Keyword
     * @return List of tasks containing keyword
     */
    public ArrayList<Task> filterTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String currentTaskName = tasks.get(i).getName();
            if (currentTaskName.contains(keyword)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }
}
