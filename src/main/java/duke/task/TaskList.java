package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public TaskList(List<String> tasks) {
        for (String t : tasks) {
            String[] data = t.split(" \\| ");
            String type = data[0];
            String mark = data[1];
            String descr = data[2];
            Task task;
            if (type.equals("T")) {
                task = new Todo(descr);
            } else if (type.equals("D")) {
                LocalDateTime dateTime = LocalDateTime.parse(data[3].trim());
                task = new Deadline(descr, dateTime);
            } else {
                LocalDateTime startDt = LocalDateTime.parse(data[3].trim());
                LocalDateTime endDt = LocalDateTime.parse(data[4].trim());
                task = new Event(descr, startDt, endDt);
            }
            if (mark.equals("X")) {
                task.markDone();
            } else {
                task.unmarkDone();
            }
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
}
