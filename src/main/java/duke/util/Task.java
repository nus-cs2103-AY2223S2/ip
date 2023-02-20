package duke.util;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A template to create tasks specified from the user,
 * such as {@code ToDo}, {@code Deadline}, {@code ScheduledEvent}
 */

public class Task {
    private String nature;
    private boolean isDone;
    private String action;

    /**
     * Constructs a task with the specified nature and action from the user.
     *
     * @param nature nature of the task as specified by the user:
     *               ToDo, Deadline, ...
     * @param action action of the task as specified by the user
     */
    public Task (String nature, String action) {
        this.nature = nature;
        this.isDone = false;
        this.action = action;
    }

    /**
     * Constructs a task with the specified nature and action from the user.
     *
     * @param nature nature of the task as specified by the user:
     *               ToDo, Deadline, ...
     * @param action action of the task as specified by the user
     * @param isDone whether a task is marked as done
     */

    public Task (String nature, String action, boolean isDone) {
        this.nature = nature;
        this.isDone = isDone;
        this.action = action;
    }

    /**
     * Mark a task as done.
     *
     * @return a task marked as done.
     */

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Mark a task as not done.
     *
     * @return a task marked as not done.
     */

    public Task unMark() {
        this.isDone = false;
        return this;
    }

    /**
     * A dummy method for deadline and event child classes to override
     *
     */

    public String getTimeInfo () {
        return "";
    }

    /**
     * Return the marking status of an event
     *
     * @return the marking status of an event
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Return the required action of an event in
     * the form of {@code String}
     *
     * @return the required action of an event
     */

    public String getAction() {
        return action;
    }

    /**
     * Return the nature of an event, whether it is
     * TODO, DEADLINE, or EVENT
     *
     * @return the nature of an event
     */

    public String getNature() {
        return nature;
    }

    /**
     * A dummy method for {@code ToDo}, {@code Deadline}, and {@code ScheduledEvent}
     * child classes to override
     *
     */

    public List<LocalDateTime> getDates() {
        return List.of();
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "[" + this.nature.charAt(0) + "]";
        if (this.isDone) {
            toPrintOut += "[X] ";
        } else {
            toPrintOut += "[ ] ";
        }
        toPrintOut += this.action;
        return toPrintOut;
    }
}
