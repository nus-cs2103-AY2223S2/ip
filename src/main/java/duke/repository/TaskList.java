package duke.repository;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor of TaskList class.
     *
     * @param taskList list of existing tasks the user have.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Alternative constructor of TaskList class.
     * Sets up TaskList in case where user does not have existing tasks
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the referenced task based on id provided.
     *
     * @param taskId id that identifies the task in the list.
     * @return task that is referenced
     */
    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }

    /**
     * Add new task.
     *
     * @param oneTask new task to be added.
     */
    public void addTask(Task oneTask) {
        taskList.add(oneTask);
    }

    /**
     * Delete task based on id provided.
     *
     * @param taskId id that identifies task to be deleted.
     */
    public void deleteTask(int taskId) {
        taskList.remove(taskId);
    }

    /**
     * Updates task status to complete based on id provided.
     *
     * @param taskId id that identifies task to be updated.
     */
    public void markTask(int taskId) {
        Task oneTask = taskList.get(taskId);
        oneTask.markTask();
    }

    /**
     * Updates task status to incomplete based on id provided.
     *
     * @param taskId id that identifies task to be updated.
     */
    public void unmarkTask(int taskId) {
        Task oneTask = taskList.get(taskId);
        oneTask.unmarkTask();
    }

    /**
     * Returns number of task currently.
     *
     * @return number of task currently.
     */
    public int numTasks() {
        return taskList.size();
    }

    /**
     * Returns string representation of all tasks in a list.
     *
     * @return string representation of all tasks in a list.
     */
    public String getTaskList() {
        String listString = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task oneTask = taskList.get(i);
            listString += "\n" + (i + 1) + ". " + oneTask.toString();
        }
        return listString;
    }
}
