package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends DukeTask {
    /**
     * The deadline date and time of the task
     */
    public final LocalDateTime deadline;
    private static final String STORAGE_FORMAT = "[D] | %s | %s | %s";
    private static final String FORMAT = "[D]%s %s ( by: %s )";

    /**
     * Constructor for DeadlineTask that takes in the information of the task and its Deadline.
     *
     * @param info     The information of the task
     * @param deadline The deadline of the task
     */
    public DeadlineTask(String info, LocalDateTime deadline) {
        super(info, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns true if the given date and time is equal to the deadline of the task.
     *
     * @param date The date and time to check
     * @return true if the date and time is equal to the deadline, false otherwise
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        LocalDate deadlineDate = this.deadline.toLocalDate();
        return date.isEqual(deadlineDate);
    }


    /**
     * Returns the deadline date and time of the task.
     *
     * @return The deadline date and time of the task
     */
    public LocalDateTime getEndDate() {
        return this.deadline;
    }

    /**
     * Returns a string representation of the task in a specific format, indicating whether the task is done or not,
     * the information of the task and the deadline.
     *
     * @return A string representation of the task
     */
    @Override
    public String storageString() {
        // Format the task status, task information, and deadline into a single string
        String isCompleted = this.getStatus() ? "[X]" : "[ ]";
        return String.format(STORAGE_FORMAT, isCompleted, this.getInformation().strip(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm")));
    }

    /**
     * Returns a string representation of the task in a specific format, indicating the task type, whether the task is
     * done or not, the information of the task and the deadline.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        String status = this.getStatus() ? "[X]" : "[ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format(FORMAT, status ,this.getInformation(), this.deadline.format(formatter));
    }
}