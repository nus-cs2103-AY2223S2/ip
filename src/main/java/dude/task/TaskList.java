package dude.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    /**
     * Initializes TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Initializes TaskList.
     *
     * @param taskList List of task to store into TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets Task in TaskList at given task index.
     *
     * @param taskIndex Index of Task.
     * @return Task at given task index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }

    /**
     * Adds Task into TaskList.
     *
     * @param task Task object to be added into TaskList.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Task.addTaskCount();
    }

    /**
     * Deletes Task from TaskList.
     *
     * @param taskIndex Index of Task to be deleted from TaskList.
     */
    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex - 1);
        Task.removeTaskCount();
    }

    /**
     * Returns raw output of TaskList.
     *
     * @return Raw output of TaskList.
     */
    public String toRaw() {
        StringBuilder input = new StringBuilder();
        for (Task task : taskList) {
            input.append(task.toRaw());
        }
        return input.toString();
    }

    /**
     * Returns string output of TaskList.
     *
     * @return String output of TaskList.
     */
    @Override
    public String toString() {
        StringBuilder result;
        if (taskList.size() != 0) {
            result = new StringBuilder("\tHere are the tasks in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                result.append("\t").append(i + 1).append(".").append(taskList.get(i).toString()).append("\n");
            }
        } else {
            result = new StringBuilder("\tEh... You currently got no task leh.\n");
        }
        return result.toString();
    }

}