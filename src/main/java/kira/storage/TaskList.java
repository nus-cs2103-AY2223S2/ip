package kira.storage;
import java.util.ArrayList;
import java.util.List;

import kira.exception.KiraException;
import kira.task.Deadline;
import kira.task.Event;
import kira.task.Task;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

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
     * @param task Task to be stored
     */
    public void store(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task as completed.
     * 
     * @param index The task to be marked as done.
     * @return Message to be printed by the bot.
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
     * @param index The task to be marked as incomplete.
     * @return Message to be printed by the bot.
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
     * @param index The task to be deleted.
     * @return Message to be printed by the bot
     * @throws KiraException Invalid-Index
     */
    public Task delete(int index) throws KiraException {
        if (index > taskList.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        return taskList.remove(index - 1);
    }

    /**
     * Find all deadlines or events that are ongoing today.
     * 
     * @return Message to be printed by the bot
     */
    public List<Task> findToday() {
        ArrayList<Task> todays = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof Deadline) {
                Deadline temp = (Deadline) t;
                if (temp.matchToday()) {
                    todays.add(temp);
                }
            } else if (t instanceof Event) {
                Event temp = (Event) t;
                if (temp.withinTimeframe()) {
                    todays.add(temp);
                }
            }
        }
        return todays;
    }

    public void save(String pathname) throws KiraException {
        SaveLoad.save(taskList, pathname);
    }
}
