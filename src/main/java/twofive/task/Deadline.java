package twofive.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a Deadline task with a given description and deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Represents a constructor for the Deadline class.
     *
     * @param taskDescription Description of the Deadline task.
     * @param deadline Deadline of the Deadline task.
     */
    public Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineString =
                " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma")) + ")";
        return "[D]" + super.toString() + deadlineString;
    }

    /**
     * {@inheritDoc}
     * The type of task and its deadline is also included.
     */
    @Override
    public String getFileWriteString() {
        return "D" + super.getFileWriteString() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean isToday(LocalDate date) {
        return this.deadline.toLocalDate().isEqual(date);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline deadlineTask = (Deadline) o;

        return super.equals(deadlineTask) && deadlineTask.deadline.equals(this.deadline);
    }

    @Override
    public ArrayList<String> getTaskDetails() {
        ArrayList<String> taskDetails = new ArrayList<>();
        taskDetails.add("Deadline");
        taskDetails.add(super.getTaskStatus());
        taskDetails.add(super.getTaskDescription());
        taskDetails.add(this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HH:mm")));
        return taskDetails;
    }
}
