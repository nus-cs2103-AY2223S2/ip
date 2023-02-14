package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;


/**
 * A deadline task object that stores its description, the due date and on whether is it done or not. The task can be
 * marked as done or unmarked as not done.
 */
public class Deadline extends Task {
    /**
     * The due date of the deadline task.
     */
    private final LocalDate by;

    /**
     * Constructor for a deadline task with the given description and due date. It checks on whether the due date is
     * in the appropriate format first. If it is not, an exception is throw to provide hints on the due date format.
     *
     * @param description The description of the deadline task
     * @param by          The due date of the deadline task
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw DukeException.DATETIME_FORMAT;
        } catch (Exception e) {
            throw new DukeException("Unknown error occurred when parsing datetime.");
        }
    }

    /**
     * Constructor for a deadline task with the given description, due date and its status of completion. It checks on
     * whether the due date is in the appropriate format first. If it is not, an exception is throw to provide hints
     * on the due date format.
     *
     * @param description The description of the deadline task
     * @param by          The due date of the deadline task
     * @param isMarked    Whether the deadline task is done or not
     */
    public Deadline(String description, String by, boolean isMarked) {
        this(description, by);
        isDone = isMarked;
    }

    /**
     * Checks on whether the string representation of the deadline task is the same format as the one exported. If it
     * is, then a new task is created with the described properties. Otherwise, return an empty task.
     *
     * @param data String representation of a deadline task
     * @return A deadline task object that describes the given data of the task
     */
    public static Task readFromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*) ; (deadline:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            String deadline = matcher.group(6);
            return new Deadline(description, deadline, isMarked);
        }
        return Task.EMPTY_TASK;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
    }

    @Override
    public String toData() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("Deadline | marked: %s ; description: %s ; deadline: %s", getMarkedStatus(),
                description, formattedBy);
    }
}
