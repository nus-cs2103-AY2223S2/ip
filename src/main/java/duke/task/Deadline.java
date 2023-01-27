package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;

import java.time.LocalDateTime;

/**
 * Subclass of task representing a deadline task
 */
public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    private Deadline(String description, String by) throws InvalidDateTimeException {
        super(description,false, "D" );
        this.dueDateTime = Parser.handleDateTime(by);
    }

    /**
     * Factory method that creates a deadline task
     *
     * @param desc The description of a deadline task
     * @return a deadline task
     * @throws InvalidDateTimeException If incorrect dateTime values are provided
     */
    public static Deadline createDeadline(String desc) throws InvalidDateTimeException {
        String[] deadlineArr = desc.split(" /by ");
        String deadlineDesc = deadlineArr[0].trim();
        String by = deadlineArr[1];
        return new Deadline(deadlineDesc, by);
    }

    /**
     * Returns the string representation of a deadline task
     *
     * @return string representation of a deadline task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s %s)", super.toString(),
                dueDateTime.toLocalDate(), dueDateTime.toLocalTime());
    }
}
