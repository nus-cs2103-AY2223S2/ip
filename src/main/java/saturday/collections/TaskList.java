package saturday.collections;
import saturday.models.Deadline;
import saturday.models.Event;
import saturday.models.Task;
import saturday.utilities.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * A TaskList is a collection of tasks that allows you to perform operations on tasks.
 * It extends the ArrayList class, so it has all the functionality of an ArrayList,
 * with additional methods to mark and unmark tasks, get a TaskList on a specific date,
 * and get and remove tasks using 1-based indexing.
 *
 * @author Titus Lowe
 * @version 0.1
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Creates an empty TaskList
     */
    public TaskList() {
        super();
    }

    /**
     * Marks the task at the specified index as done
     *
     * @param i index of task to be marked as done (1-based indexing)
     */
    public void mark(int i) { super.get(i-1).mark(); }

    /**
     * Unmarks the task at the specified index as done
     *
     * @param i index of task to be unmarked (1-based indexing)
     */
    public void unMark(int i) { super.get(i-1).unMark(); }

    /**
     * Returns a TaskList containing all tasks that are on the specified date
     *
     * @param date date to filter tasks by
     * @return TaskList of tasks on the specified date
     */
    public TaskList getTaskListOnDate(String date) {
        TemporalAccessor inputDate = DateTimeParser.parseDate(date);
        TaskList taskListOnDate = new TaskList();
        if (inputDate instanceof LocalDate) {
            Iterator<Task> iterator = this.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task instanceof Deadline) {
                    TemporalAccessor deadline = ((Deadline) task).getDeadline();
                    if (deadline instanceof LocalDate) {
                        if (((LocalDate) inputDate).isEqual((LocalDate) deadline)) {
                            taskListOnDate.add(task);
                        }
                    } else if (deadline instanceof LocalDateTime) {
                        if (((LocalDate) inputDate).isEqual(((LocalDateTime) deadline).toLocalDate())) {
                            taskListOnDate.add(task);
                        }
                    }
                } else if (task instanceof Event) {
                    TemporalAccessor from = ((Event) task).getFrom();
                    TemporalAccessor to = ((Event) task).getTo();
                    if (from instanceof LocalDate && to instanceof LocalDate) {
                        if (((LocalDate) inputDate).isEqual((LocalDate) from) || ((LocalDate) inputDate).isEqual((LocalDate) to)) {
                            taskListOnDate.add(task);
                        }
                    } else if (from instanceof LocalDateTime && to instanceof LocalDateTime) {
                        if (((LocalDate) inputDate).isEqual(((LocalDateTime) from).toLocalDate()) || ((LocalDate) inputDate).isEqual(((LocalDateTime) to).toLocalDate())) {
                            taskListOnDate.add(task);
                        }
                    }
                }
            }
        }
        return taskListOnDate;
    }

    /**
     * Returns the task at the specified index
     *
     * @param i index of the task to return (1-based indexing)
     * @return the task at the specified index
     */
    @Override
    public Task get(int i) { return super.get(i-1); }

    /**
     * Removes the task at the specified index
     *
     * @param i index of the task to remove (1-based indexing)
     * @return the removed task
     */
    @Override
    public Task remove(int i) {
        Task removedTask = super.remove(i-1);
        return removedTask;
    }

    /**
     * Returns a string representation of the TaskList
     *
     * @return a string representation of the TaskList
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this) {
            if (i == size()) {
                sb.append(i+".").append(task).append("\t");
            } else {
                sb.append(i + ".").append(task).append("\n\t");
                i++;
            }
        }
        return sb.toString();
    }
}
