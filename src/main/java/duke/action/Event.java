package duke.action;
import java.time.LocalTime;
import java.util.Arrays;

import duke.data.TypeOfTask;
import duke.exception.DukeException;

/**
 * Represents event class that tracks the description, start time and end time of the task
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Event extends Task {
    private LocalTime startTime;
    private LocalTime endTime;
    private String startDay;
    private String endDay;

    /**
     * Represents default constructor that takes in description,start time and end time of the task
     *
     * @param description Description of the task
     * @param startTime Start time of the task
     * @param endTime End time of the task
     * @throws DukeException When convertToLocalTime cannot be computed due to unacceptable inputs
     */
    public Event(String description, String startTime, String endTime) throws DukeException {
        super(description, TypeOfTask.event);
        String[] startTimeArray = startTime.split(" ");
        String[] endTimeArray = endTime.split(" ");
        // Example: /by Monday /to Tuesday
        if (startTimeArray.length == 1 && endTimeArray.length == 1) {
            this.startDay = startTimeArray[0];
            this.startTime = null;
            this.endDay = endTimeArray[0];
            this.endTime = null;
        } else if (startTimeArray.length == 2 && endTimeArray.length == 2) {
            // Example: /by Monday 2 pm /to Sunday 4 pm
            this.startDay = startTimeArray[0];
            String slicedStartTime = String.join(" ", Arrays.copyOfRange(startTimeArray, 1, startTimeArray.length));
            String slicedEndTime = String.join(" ", Arrays.copyOfRange(endTimeArray, 1, endTimeArray.length));
            this.startTime = super.parser.convertToLocalTime(slicedStartTime);
            this.endDay = endTimeArray[0];
            this.endTime = super.parser.convertToLocalTime(slicedEndTime);
        } else if (startTimeArray.length > 2 && endTimeArray.length == 2) {
            // Example: /by Mon 2 pm /to 4 pm
            this.startDay = startTimeArray[0];
            String slicedStartTime = String.join(" ", Arrays.copyOfRange(startTimeArray, 1, startTimeArray.length));
            String slicedEndTime = String.join(" ", endTimeArray);
            this.startTime = super.parser.convertToLocalTime(slicedStartTime);
            this.endDay = null;
            this.endTime = super.parser.convertToLocalTime(slicedEndTime);
        } else {
            throw new DukeException(TypeOfTask.event, 1);
        }
    }

    /**
     * Prints the details of the Event
     *
     * @return details of the Event
     */
    @Override
    public String toString() {
        if (startTime == null) {
            return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcon(),
                    super.getDescription(), this.startDay, this.endDay);
        } else if (endDay == null) {
            String startTime = this.startTime.toString();
            String endTime = this.endTime.toString();
            return String.format("[E][%s] %s (from: %s %s to: %s)", super.getStatusIcon(), super.getDescription(),
                    this.startDay, startTime, endTime);
        } else {
            String startTime = this.startTime.toString();
            String endTime = this.endTime.toString();
            return String.format("[E][%s] %s (from: %s %s to: %s %s)", super.getStatusIcon(), super.getDescription(),
                    this.startDay, startTime, this.endDay, endTime);
        }
    }
}
