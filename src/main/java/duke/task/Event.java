package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.TaskType;

/**
 * Tasks that place from a certain date and time to another date and time.
 */
public class Event extends Task {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    private Event(String description, LocalDateTime start, LocalDateTime end) {
        super(TaskType.EVENT, description);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method of Event objects.
     * @param str String containing description of event and start and end dates and times.
     * @return Event object.
     */
    public static Event to(String str) {
        String target1 = " /from ";
        String target2 = " /to ";
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        int index1 = str.indexOf(target1);
        int index2 = str.indexOf(target2);
        String description = str.substring(0, index1);
        String start = str.substring(index1 + 7, index2);
        String end = str.substring(index2 + 5);

        int startFirstSlash = start.indexOf("/");
        int startSecondSlash = start.indexOf("/", startFirstSlash + 1);
        int endFirstSlash = end.indexOf("/");
        int endSecondSlash = end.indexOf("/", endFirstSlash + 1);

        String startDay = startFirstSlash == 1 ? "d" : "dd";
        String startMonth = startSecondSlash - startFirstSlash == 2 ? "M" : "MM";
        String endDay = endFirstSlash == 1 ? "d" : "dd";
        String endMonth = endSecondSlash - endFirstSlash == 2 ? "M" : "MM";

        DateTimeFormatter startInFormatter = DateTimeFormatter.ofPattern(startDay + "/" + startMonth + "/yyyy HHmm");
        DateTimeFormatter endInFormatter = DateTimeFormatter.ofPattern(endDay + "/" + endMonth + "/yyyy HHmm");
        try {
            startDateTime = LocalDateTime.parse(start, startInFormatter);
            endDateTime = LocalDateTime.parse(end, endInFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date and time! Please try again!");
        }

        if (startDateTime.isAfter(endDateTime)) {
            throw new RuntimeException("Unable to create duke.task.Event! "
                    + "Starting date and time cannot be after ending date and time!");
        }
        return new Event(description, startDateTime, endDateTime);
    }

    /**
     * Creates a string representing the event object so that it can be saved by Storage object.
     * @return String representing the event object.
     */
    @Override
    public String taskToSavedForm() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "event " + this.description + " /from " + this.start.format(outFormatter)
                + " /to " + this.end.format(outFormatter) + marked;
    }

    /**
     * Update /by field.
     * @param date The update.
     */
    @Override
    public void updateByField(String date) {
        throw new RuntimeException("Unable to update! Task not a deadline!");
    }

    /**
     * Update /from field.
     * @param date The update.
     */
    @Override
    public void updateFromField(String date) {
        LocalDateTime newDateTime;
        int firstSlash = date.indexOf("/");
        int secondSlash = date.indexOf("/", firstSlash + 1);
        String day = firstSlash == 1 ? "d" : "dd";
        String month = secondSlash - firstSlash == 2 ? "M" : "MM";
        DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(day + "/" + month + "/yyyy HHmm");
        try {
            newDateTime = LocalDateTime.parse(date, inFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date and time! Please try again!");
        }
        if (newDateTime.isAfter(this.end)) {
            throw new RuntimeException("Starting date and time cannot be after ending date and time!");
        }
        this.start = newDateTime;
    }

    /**
     * Update /to field.
     * @param date The update.
     */
    @Override
    public void updateToField(String date) {
        LocalDateTime newDateTime;
        int firstSlash = date.indexOf("/");
        int secondSlash = date.indexOf("/", firstSlash + 1);
        String day = firstSlash == 1 ? "d" : "dd";
        String month = secondSlash - firstSlash == 2 ? "M" : "MM";
        DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(day + "/" + month + "/yyyy HHmm");
        try {
            newDateTime = LocalDateTime.parse(date, inFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date and time! Please try again!");
        }
        if (newDateTime.isBefore(this.start)) {
            throw new RuntimeException("Ending date and time cannot be before starting date and time!");
        }
        this.end = newDateTime;
    }

    /**
     * Creates custom string containing Event object's description and start and end dates and times.
     * @return String representing Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String str = super.toString();
        str += " (from: " + this.start.format(outFormatter) + " to: " + this.end.format(outFormatter) + ")";
        return str;
    }
}
