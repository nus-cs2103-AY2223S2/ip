package cbot.task;

import java.time.LocalDateTime;

/**
 * Default (To-Do) task. Stores the description of the task, and whether it has been completed or not.
 *
 * @see Deadline
 * @see Event
 */
public class Task implements Comparable<Task> {
    public static final String DONE_TRUE = "X";
    public static final String DONE_FALSE = " ";
    public static final String SEP = " ;; ";
    public static final String TODO_SYMBOL = "T";

    private final String desc;
    private boolean isDone;

    /**
     * Constructs a 'to-do' task with the given description and completion status.
     * The last variable is optional, and defaults to false (not done)
     *
     * @param desc The task description.
     * @param isDone Whether the task is done.
     */
    public Task(String desc, boolean ... isDone) {
        this.desc = desc;
        this.isDone = isDone.length != 0 && isDone[0];
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDesc() {
        return this.desc;
    }


    /**
     * Returns the symbol corresponding to the task type.
     * "T" for To-Do, "D" for Deadline, and "E" for Event.
     *
     * @return The task type's symbol.
     */
    public String getSymbol() {
        return TODO_SYMBOL;
    }

    /**
     * Returns the symbol corresponding to the task's completion status.
     * "X" for done/marked, and whitespace (" ") for not-yet-done/unmarked.
     *
     * @return The completion status symbol.
     */
    public String getStatus() {
        return this.isDone ? DONE_TRUE : DONE_FALSE;
    }

    /**
     * Returns true if the task has a time attached to it (Deadline, Event), and false otherwise (To-Do).
     *
     * @return Whether the task is time-based.
     */
    public boolean hasTime() {
        return false;
    }

    /**
     * Returns the 'main' datetime of the task. i.e. the due date of a Deadline, and the start of an Event.
     * Returns the earliest supported datetime (LocalDateTime.MIN) for non-time-based (To-Do) tasks.
     *
     * @return The datetime of the task.
     */
    public LocalDateTime getTime() {
        return LocalDateTime.MIN;
    }

    /**
     * Returns the string representation of the task, to be displayed for a user.
     * This provides its type, status, description, and time (if applicable).
     *
     * @return The string representation of the task, for reading.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getSymbol(), getStatus(), getDesc());
    }

    /**
     * Returns the string representation of the task, to be saved in a file.
     * This provides its type, status, description, and time (if applicable).
     *
     * @return The string representation of the task, for saving.
     * @see cbot.io.FileStuff
     */
    public String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                getSymbol(), SEP, getStatus(), SEP, getDesc());
    }

    /**
     * Marks the task as done.
     *
     * @return Whether the status changed from not done to done.
     */
    public boolean yesDo() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    /**
     * Marks the task as not-yet-done.
     *
     * @return Whether the status changed from done to not done.
     */
    public boolean noDo() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compares this task with the other task.
     * Tasks are first compared by time (i.e. {@link #getTime()}, then lexicographically.
     *
     * @param other The task to be compared to.
     * @return A negative integer, zero, or a positive integer if this is
     *         less than, equal to, or greater than the other.
     */
    @Override
    public int compareTo(Task other) {
        if (this.getTime().isEqual(other.getTime())) {
            return getDesc().toLowerCase().compareTo(other.getDesc().toLowerCase());
        }

        return getTime().compareTo(other.getTime());
    }
}
