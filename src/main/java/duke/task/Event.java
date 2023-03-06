package duke.task;

import java.util.regex.PatternSyntaxException;

/**
 * Represents an Event task
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Returns an Event with task stored as not done
     *
     * @param cmd String with format event [TASK] /from [WHEN] /to [WHEN]
     * @throws PatternSyntaxException if the command was not formatted correctly
     * @throws ArrayIndexOutOfBoundsException if the command was not formatted correctly
     */
    public Event(String cmd) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd, Task.IS_DONE);
    }

    /**
     * Returns an Event with task and isDone stored
     *
     * @param cmd String with format event [TASK] /from [WHEN] /to [WHEN]
     * @param isDone boolean of if the Event is done
     * @throws PatternSyntaxException if the command was not formatted correctly
     * @throws ArrayIndexOutOfBoundsException if the command was not formatted correctly
     */
    public Event(String cmd, boolean isDone) throws PatternSyntaxException, ArrayIndexOutOfBoundsException {
        this(cmd.split(" /")[0],
                cmd.split(" /")[1].replace("from ", ""),
                cmd.split(" /")[2].replace("to ", ""),
                isDone);
    }

    /**
     * Returns an Event with the task, from, to and isDone stored immediately
     *
     * @param task String of Event description to be stored
     * @param from String of when Event starts
     * @param to String of when Event ends
     * @param isDone boolean of if the Event is done
     */
    public Event(String task, String from, String to, boolean isDone) {
        super(task, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Return the String of the Event formatted to be displayed
     *
     * @return String formatted String
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    /**
     * Return the String of the Event used to be saved
     *
     * @return String formatted String
     */
    @Override
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("E | %d | %s | %s-%s", done, getTask(), from, to);
    }
}
