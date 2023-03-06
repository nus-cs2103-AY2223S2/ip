package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {
    private static final long DEFAULT_SNOOZE_DAYS = 1;
    private LocalDateTime deadline;

    /**
     * Returns a Deadline stored as not done
     *
     * @param cmd String with format deadline [TASK] /by yyyy-mm-ddThh:mm:ss
     * @throws PatternSyntaxException if the command was not formatted correctly
     * @throws ArrayIndexOutOfBoundsException if the command was not formatted correctly
     * @throws DateTimeParseException if the deadline was not formatted correctly
     */
    public Deadline(String cmd)
            throws PatternSyntaxException, ArrayIndexOutOfBoundsException, DateTimeParseException {
        this(cmd, Task.IS_DONE);
    }

    /**
     * Returns a Deadline with task and isDone stored
     *
     * @param cmd String with format deadline [TASK] /by yyyy-mm-ddThh:mm:ss
     * @param isDone boolean of if the Deadline is done
     * @throws PatternSyntaxException if the command was not formatted correctly
     * @throws ArrayIndexOutOfBoundsException if the command was not formatted correctly
     * @throws DateTimeParseException if the deadline was not formatted correctly
     */
    public Deadline(String cmd, boolean isDone)
            throws PatternSyntaxException, ArrayIndexOutOfBoundsException, DateTimeParseException {
        this(cmd.split(" /by ")[0],
                cmd.split(" /by ")[1],
                isDone);
    }

    /**
     * Returns a Deadline with task, deadline and isDone stored
     *
     * @param task String of Deadline description to be stored
     * @param deadlineString String of deadline in format yyyy-mm-ddThh:mm:ss
     * @param isDone boolean of if the Deadline is done
     * @throws PatternSyntaxException if the command was not formatted correctly
     * @throws ArrayIndexOutOfBoundsException if the command was not formatted correctly
     @throws DateTimeParseException if the deadline was not formatted correctly
     */
    public Deadline(String task, String deadlineString, boolean isDone)
            throws PatternSyntaxException, ArrayIndexOutOfBoundsException, DateTimeParseException {
        super(task, isDone);
        this.deadline = LocalDateTime.parse(deadlineString);
    }
    @Override
    public void snooze() {
        deadline = deadline.plusDays(DEFAULT_SNOOZE_DAYS);
    }
    @Override
    public boolean isDeadline() {
        return true;
    }

    /**
     * Return the String of the Deadline formatted to be displayed
     *
     * @return String formatted String
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)", super.toString(), deadline);
    }

    /**
     * Return the String of the Deadline used to be saved
     *
     * @return String formatted String
     */
    @Override
    public String saveString() {
        int done = isDone() ? 1 : 0;
        return String.format("D | %d | %s | %s", done, getTask(), deadline);
    }
}