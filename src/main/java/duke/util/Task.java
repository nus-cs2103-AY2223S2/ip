package duke.util;

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

    public String getAdditionalInfo () {
        return "";
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getAction() {
        return action;
    }

    public String getNature() {
        return nature;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "[" + this.nature + "]";
        if (this.isDone) {
            toPrintOut += "[X] ";
        } else {
            toPrintOut += "[ ] ";
        }
        toPrintOut += this.action;
        return toPrintOut;
    }
}
