package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The TaskList class represent a list of all the tasks added by the user.
 *
 * @author Chia Jeremy
 */
public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>(100);

    /**
     * Class constructor of the task list.
     *
     * @param tasks the list of saved tasks
     */
    public TaskList(ArrayList<String> tasks) {
        for (String t : tasks) {
            Task task = processTask(t);
            add(task);
        }
    }

    /**
     * Adds the task into the task list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
        Collections.sort(this.taskList);
    }

    /**
     * Delete the task from the task list.
     *
     * @param index the index of the task to delete
     */
    public void delete(int index) {
        this.taskList.remove(index);
    }

    /**
     * Mark the task.
     *
     * @param index the index of the task to mark
     */
    public void mark(int index) {
        getTask(index).markDone();
    }

    /**
     * Unmark the saved task.
     *
     * @param index the index of the task to unmark
     */
    public void unmark(int index) {
        getTask(index).unmarkDone();
    }

    /**
     * Returns all tasks from the task list.
     *
     * @return all tasks from the task list
     */
    public ArrayList<Task> get() {
        return this.taskList;
    }

    /**
     * Returns a task from the task list.
     *
     * @param index the index of the task
     * @return a task
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size of the list
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns true if task list is empty; otherwise, false.
     *
     * @return true if task list is empty; otherwise, false
     */
    public Boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    private Task processTask(String s) {
        String[] data = s.split(" \\| ");
        String type = data[0];
        String mark = data[1];
        String descr = data[2];
        Task task;
        if (type.equals("T")) {
            task = new Todo(descr);
        } else if (type.equals("D")) {
            String dt = data[3].trim();
            LocalDateTime dateTime = LocalDateTime.parse(dt);
            task = new Deadline(descr, dateTime);
        } else {
            String startDt = data[3].trim();
            String endDt = data[4].trim();
            LocalDateTime startDateTime = LocalDateTime.parse(startDt);
            LocalDateTime endDateTime = LocalDateTime.parse(endDt);
            task = new Event(descr, startDateTime, endDateTime);
        }
        if (mark.equals("X")) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
        return task;
    }
}
