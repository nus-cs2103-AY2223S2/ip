package rick.task;

import java.time.LocalDate;

import rick.RickUtils;

/**
 * Represents a template for the Tasks stored by the Rick class.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public abstract class RickTask {
    private boolean isDone = false;
    private final String value;

    /**
     * Constructs a Task instance.
     *
     * @param val The task to be completed.
     */
    public RickTask(String val) {
        this.value = val;
    }

    /**
     * Modifies this task instance to indicate that it is done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Modifies this task instance to indicate that it is not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Generates and returns a human friendly interpretation of this task.
     *
     * @return The task interpretation.
     */
    @Override
    public String toString() {
        return String.format(
                "[%s] %s", (this.isDone ? "X" : " "), this.value
        );
    }

    /**
     * Formats this task as a "|" separated string to be stored
     * in a file storage, and returns the formatted schema.
     *
     * @return The formatted string.
     */
    public String toDbSchema() {
        return String.format(
                "%s|%s",
                this.isDone ? 1 : 0,
                this.value
        );
    }

    /**
     * Parses an object read from the file storage,
     * and returns the task it represents.
     *
     * @param o The object read from file storage.
     * @return The corresponding task record.
     */
    public static RickTask fromDbSchema(Object o) {
        String[] s = o.toString().split("\\|");
        boolean isDone = s[1].equals("1");
        switch (s[0]) {
        case "E":
            RickTask event = new EventTask(
                    s[2],
                    RickUtils.parseDateTime(s[3]),
                    RickUtils.parseDateTime(s[4]));
            if (isDone) {
                event.setDone();
            }
            return event;
        case "D":
            RickTask deadline = new DeadlineTask(s[2],
                    RickUtils.parseDateTime(s[3]));
            if (isDone) {
                deadline.setDone();
            }
            return deadline;
        case "T":
            RickTask todo = new TodoTask(s[2]);
            if (isDone) {
                todo.setDone();
            }
            return todo;
        default:
            return null;
        }
    }

    /**
     * Return a boolean indicating if this task's description contains the
     * given term.
     *
     * @param term The provided term.
     * @return The indicative boolean.
     */
    public boolean containsTerm(String term) {
        return this.value.contains(term);
    }

    /**
     * Returns a boolean indicating if this task falls on the given date.
     *
     * @param dt The given date.
     * @return The indicative boolean.
     */
    public abstract boolean isOnDate(LocalDate dt);
}
