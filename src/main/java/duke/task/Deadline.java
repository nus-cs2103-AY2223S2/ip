package duke.task;

import duke.UI.TextOutput;
import duke.exception.DukeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents task of the type 'deadline'.
 */
public class Deadline extends Task {
    private LocalDateTime time;

    /**
     * Constructs a new Deadline instance with the given content, date and isDone state.
     * <p>
     * It also stores the string data as a LocalDateTime object.
     * @param content content of the task.
     * @param date deadline of the task.
     * @param isDone whether the task is done.
     */
    public Deadline(String content, String date, boolean isDone) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        format.withLocale(Locale.ENGLISH);
        this.time = LocalDateTime.parse(date, format);
        this.taskType = this.taskType.D;
    }

    /**
     * Constructs the string representation of the Deadline object.
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        String time = this.time.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.getTaskContent() + " (by: " + time + ")";
    }

    /**
     * Implements the update task time functionality.
     * @param dates The new dates.
     * @return The updated task string representation or error notifications.
     * @throws DukeException
     */
    @Override
    public String updateTaskTime(String ... dates) throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        format.withLocale(Locale.ENGLISH);
        try {
            this.time = LocalDateTime.parse(dates[0] + " " + dates[1], format);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format.");
        }
        return TextOutput.makePostponeString(this);
    }
}

