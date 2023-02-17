package duke.task;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;


/**
 * TaskList to contain all the tasks in a list
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskListInput) throws DukeException {
        this.taskList = taskListInput;
    }


    public List<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }


    /**
     * Prints all the tasks in the taks list
     */
    public String printList() {
        if (taskList.size() == 0) {
            return "There is no task in your task list!";
        }
        String list = "Here are some tasks in your list:";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            list = list + "\n" + i + "." + (taskList.get(i - 1)).toString();
        }
        return list;
    }

    /**
     * Mark the task stored in the list as completed
     * @param taskNum Task number of task in the list to mark as completed
     */
    public void markTask(int taskNum) throws IndexOutOfBoundsException {
        Task originalTask = taskList.get(taskNum);
        originalTask.markTask();
    }

    /**
     * Mark the task stored in the list as not completed
     * @param taskNum Task number of task in the list to mark as completed
     */
    public void unmarkTask(int taskNum) throws IndexOutOfBoundsException {
        Task originalTask = taskList.get(taskNum);
        originalTask.unmarkTask();
    }

    /**
     * Add the task into the list of task
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }


    /**
     * Delete the task from the list
     * @param taskNum Task number of task in the list to be deleted
     */
    public void deleteTask(int taskNum) throws IndexOutOfBoundsException {
        taskList.remove(taskNum);
    }

    /**
     * Check if the task has exact same details with any of the existing tasks
     * @param task task to be checked
     * @return True if there is an existing task that has the same details as the task being checked
     */
    public boolean checkDuplicates(Task task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if ((taskList.get(i)).equals(task)) {
                return true;
            }
        }
        return false;
    }




}
