package duke.task;

import java.time.LocalDateTime;

/**
 * The Task class represents a single task added by the user.
 *
 * @author Chia Jeremy
 */
public abstract class Task implements Comparable<Task> {

    protected String description;
    protected boolean isMark;

    /**
     * Class constructor of a task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isMark = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isMark = true;
    }

    /**
     * Unmark task as done.
     */
    public void unmarkDone() {
        this.isMark = false;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true if task is mark done; otherwise, false.
     *
     * @return true if task is mark done; otherwise, false
     */
    public Boolean isMark() {
        return isMark;
    }

    /**
     * Compare this task with a given task t.
     *
     * @param t the other task to compare to
     * @return -1 if this task comes before t;
     *          0 if this task is the same order as t;
     *          1 if this task comes after t
     */
    @Override
    public int compareTo(Task t) {
        if (isSameTaskType(this, t)) {
            return sortSameTaskTypeByDate(this, t);
        } else if (isComesBefore(this, t)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns true if both tasks are of the same class; otherwise, false.
     *
     * @param task        the current task
     * @param compareTask the task to be compared to
     * @return true if both objects are of the same class; otherwise, false
     */
    private Boolean isSameTaskType(Task task, Task compareTask) {
        return task.getClass() == compareTask.getClass();
    }

    /**
     * Tasks are arranged in the order of todo, deadline, and event.
     * Returns true if task comes before compareTask; otherwise, false.
     *
     * @param task        the current task
     * @param compareTask the task to be compared to
     * @return true if task comes before; otherwise false
     */
    private Boolean isComesBefore(Task task, Task compareTask) {
        return ((task.getClass() == Todo.class) && (compareTask.getClass() == Deadline.class))
                || ((task.getClass() == Todo.class) && (compareTask.getClass() == Event.class))
                || ((task.getClass() == Deadline.class) && (compareTask.getClass() == Event.class));
    }

    /**
     * Sort tasks of same type by chronological order.
     *
     * @param task        the current task
     * @param compareTask the task to be compared to
     * @return -1 if task's date is before compareTask;
     *          0 if task do not have date;
     *          1 if task's date comes after compareTask
     */
    private int sortSameTaskTypeByDate(Task task, Task compareTask) {
        if (task.getClass() == Todo.class) {
            return 0; // Todo do not have date
        } else if (task.getClass() == Deadline.class) {
            LocalDateTime taskDt = ((Deadline) task).getDateTime();
            LocalDateTime compareDt = ((Deadline) compareTask).getDateTime();
            return taskDt.compareTo(compareDt);
        } else {
            LocalDateTime taskStartDt = ((Event) task).getStartDt();
            LocalDateTime compareStartDt = ((Event) compareTask).getStartDt();
            return taskStartDt.compareTo(compareStartDt);
        }
    }
}
