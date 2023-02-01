package twofive.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an Event task with a given description, start time and end time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Represents a constructor for the Event class.
     *
     * @param taskDescription Description of the Event task.
     * @param startTime Start time of the Event task.
     * @param endTime End time of the Event task.
     */
    public Event(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String eventString = " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma")) + ")";
        return "[E]" + super.toString() + eventString;
    }

    /**
     * {@inheritDoc}
     * The type of task, its start time and its end time is also included.
     */
    @Override
    public String getFileWriteString() {
        return "E" + super.getFileWriteString() + " | "
                + this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean isToday(LocalDate date) {
        return this.startTime.toLocalDate().isEqual(date) || this.endTime.toLocalDate().isEqual(date);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event event = (Event) o;

        return super.equals(event) && event.startTime.equals(this.startTime) && event.endTime.equals(this.endTime);
    }

    @Override
    public ArrayList<String> getTaskDetails() {
        ArrayList<String> taskDetails = new ArrayList<>();
        taskDetails.add("Event");
        taskDetails.add(super.getTaskStatus());
        taskDetails.add(super.getTaskDescription());
        taskDetails.add(this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HH:mm")));
        taskDetails.add(this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return taskDetails;
    }
}
