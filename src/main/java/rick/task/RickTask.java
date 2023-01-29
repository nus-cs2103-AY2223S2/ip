package rick.task;

import rick.RickUtils;

import java.time.LocalDate;

/**
 * The abstraction behind the tasks stored by the rick.Rick chat-bot.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public abstract class RickTask {
    private boolean isDone = false;
    private final String value;

    /**
     * Provides a base constructor common to all task implementations that
     * stores a task and if it is done.
     *
     * @param val The task to be completed.
     */
    public RickTask(String val) {
        this.value = val;
    }

    /**
     * Indicate that this task is done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Indicate that this task is not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Human friendly interpretation of this task.
     *
     * @return The task interpretation.
     */
    @Override
    public String toString() {
        return String.format(
                "[%s] %s", (this.isDone ? "X": " "), this.value
        );
    }

    /**
     * Formats this task as a "|" separated string to be stored
     * in a file storage.
     *
     * @return The formatted string.
     */
    public String toDbSchema() {
        return String.format(
                "%s|%s",
                this.isDone? 1 : 0,
                this.value
        );
    }

    /**
     * Given an object read from the file storage, parses it
     * and returns the appropriate task.
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
        }
        return null;
    }

    /**
     * Indicate if this task's description contains the given term.
     * @param term The provided term.
     * @return The indicative boolean.
     */
    public boolean containsTerm(String term) {
        return this.value.contains(term);
    }

    /**
     * Indicate if this task falls on the given date.
     *
     * @param dt The given date.
     * @return The indicative boolean.
     */
    public abstract boolean isOnDate(LocalDate dt);
}
