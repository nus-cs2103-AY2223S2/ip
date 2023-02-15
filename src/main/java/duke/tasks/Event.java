package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task
 */
public class Event extends Task {
    private LocalDateTime toDate;
    private LocalDateTime fromDate;

    /**
     * Initializes an Event object with task description,
     * and the dates of event
     *
     * @param taskText the task description
     * @param fromDate the date of start
     * @param toDate the date of end
     */
    public Event(String taskText, LocalDateTime fromDate, LocalDateTime toDate) {
        super(taskText);
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    /**
     * Returns the string representation of the Event
     * with formatted date
     *
     * @return the string
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }

    /**
     * Returns the string representation of the Event data to
     * be stored in the text file
     *
     * @return the formatted string
     */
    public String writeFile() {
        return String.format("E|%s|%s|%s|%s", this.getCurrentStatus(), this.getTaskText(),
                this.fromDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                this.toDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    /**
     * Returns the start date of the Event
     *
     * @return the LocalDateTime date and time
     */
    public LocalDateTime getDate() {
        return this.fromDate;
    }

    /**
     * Returns the letter representation for Event type
     * to store in the text file
     *
     * @return the string letter
     */
    public String getTaskType() {
        return "E";
    }
}
