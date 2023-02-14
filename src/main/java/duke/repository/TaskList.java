package duke.repository;

import java.util.ArrayList;
import java.util.Collections;

import duke.tasks.Task;

/**
 * Holds all user tasks.
 */
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

    public ArrayList<Task> getMultipleTasks(ArrayList<Integer> taskIds) {
        ArrayList<Task> selectedTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskIds.contains(i)) {
                selectedTasks.add(taskList.get(i));
            }
        }
        return selectedTasks;
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
     * @param taskIds id that identifies task to be deleted.
     */
    public void deleteTasks(ArrayList<Integer> taskIds) {
        Collections.sort(taskIds);
        for (int i = 0; i < taskIds.size(); i++) {
            taskList.remove(taskIds.get(i) - i);
        }
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

    /**
     * Returns tasks whose description contains the string.
     *
     * @param searchStr
     * @return String list of matching tasks
     */
    public String getMatchingTasks(String searchStr) {
        String listString = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task oneTask = taskList.get(i);
            if (oneTask.getDescription().contains(searchStr)) {
                // Index maintained for easy reference for user
                listString += "\n" + (i + 1) + ". " + oneTask.toString();
            }
        }
        return listString;
    }

}
