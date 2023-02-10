package kira.storage;

import java.util.ArrayList;
import java.util.List;

import kira.exception.KiraException;
import kira.task.Deadline;
import kira.task.Event;
import kira.task.Task;

/**
 * TaskList holds and records the list of tasks.
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructs an empty tasklist.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a tasklist with the given list of task.
     *
     * @param taskList
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getTotal() {
        return taskList.size();
    }

    public List<Task> getList() {
        return taskList;
    }

    /**
     * Stores the Task into the ArrayList.
     *
     * @param task task to be stored
     */
    public void store(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task as completed.
     *
     * @param index task to be marked as done
     * @return task at index
     * @throws KiraException Invalid-Index
     */
    public Task mark(int index) throws KiraException {
        if (index > taskList.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = taskList.get(index - 1);
        currentTask.mark();

        return currentTask;
    }

    /**
     * Marks the task as incomplete.
     *
     * @param index task to be marked as incomplete
     * @return task at index
     * @throws KiraException Invalid-Index
     */
    public Task unmark(int index) throws KiraException {
        if (index > taskList.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = taskList.get(index - 1);
        currentTask.unmark();

        return currentTask;
    }

    /**
     * Deletes the task from the taskList.
     *
     * @param index task to be deleted
     * @return task at index
     * @throws KiraException Invalid-Index
     */
    public Task delete(int index) throws KiraException {
        if (index > taskList.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        return taskList.remove(index - 1);
    }

    /**
     * Finds all deadlines or events that are ongoing today.
     *
     * @return list of all tasks ongoing today
     */
    public List<Task> findToday() {
        ArrayList<Task> todayTasks = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof Deadline) {
                Deadline temp = (Deadline) t;
                if (temp.matchToday()) {
                    todayTasks.add(temp);
                }
            } else if (t instanceof Event) {
                Event temp = (Event) t;
                if (temp.withinTimeframe()) {
                    todayTasks.add(temp);
                }
            }
        }
        return todayTasks;
    }

    /**
     * Saves the tasklist as a csv file.
     *
     * @param pathname dir of output file
     * @throws KiraException File-Output-Issue
     */
    public void save(String pathname) throws KiraException {
        SaveLoad.save(taskList, pathname);
    }

    /**
     * Finds all the tasks related to the keyword.
     *
     * @param keyword
     * @return
     */
    public List<Task> findByKey(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : taskList) {
            if (t.contains(keyword)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

}
