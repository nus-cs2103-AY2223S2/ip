package duke.task;

import duke.UI.TextOutput;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;


/**
 * Represents task of the type 'Event'.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event instance with the given content, from_date and isDone state and
     * stores the string data as a LocalDateTime object.
     * @param content The content of the event.
     * @param isDone Whether the task is completed.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.taskType = taskType.E;
        this.time = this.to;
    }

    /**
     * Constructs the string representation of the Event object.
     * @return String The string representation of the Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm", Locale.ENGLISH);
        String from = this.from.format(format);
        String to = this.to.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.getTaskContent() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Implements the update task time functionality.
     * @param dates The new dates.
     * @return The updated task string representation or error notifications.
     */
    @Override
    public String updateTaskTime(String ... dates) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
        this.from = LocalDateTime.parse(dates[0] + " " + dates[1], format);
        this.to = LocalDateTime.parse(dates[2] + " " + dates[3], format);
        this.time = this.to;
        return TextOutput.makePostponeString(this);
    }
}
