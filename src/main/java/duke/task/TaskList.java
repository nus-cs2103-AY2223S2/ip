package duke.task;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public TaskList(List<Task> tasklist) throws DukeException {
        this.taskList = tasklist;
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
     * Prints the list of tasks
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
    public void deleteTask(int taskNum) throws IndexOutOfBoundsException{
        taskList.remove(taskNum);
    }

    public boolean checkDuplicates(Task task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if ((taskList.get(i)).equals(task)) {
                return true;
            }
        }
        return false;
    }




}
