package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDate;

    /**
     * Initializes a Deadline object
     *
     * @param taskText the task description
     * @param deadlineDate the deadline date and time
     */
    public Deadline(String taskText, LocalDateTime deadlineDate) {
        super(taskText);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }

    /**
     * Returns the string representation of the Deadline data to
     * be stored in the text file
     *
     * @return the formatted string
     */
    public String writeFile() {
        return String.format("D|%s|%s|%s", this.getCurrentStatus(), this.getTaskText(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    /**
     * Returns the deadline date/time of the Event
     *
     * @return the LocalDateTime date and time
     */
    public LocalDateTime getDate() {
        return this.deadlineDate;
    }

    /**
     * Returns the letter representation for Deadline type
     * to store in the text file
     *
     * @return the string letter
     */
    public String getTaskType() {
        return "D";
    }
}
