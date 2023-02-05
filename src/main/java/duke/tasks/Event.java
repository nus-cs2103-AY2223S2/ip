package duke.tasks;

import java.time.LocalDate;

/**
 * Represents an Event Task, which will happen between a start and end date and time
 */
public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String startTime;
    private final String endTime;
    private final String[] startingPeriod;
    private final String[] endingPeriod;

    /**
     * Splits inputs into smaller parts and initialises its variables
     *
     * @param name         name of the task
     * @param startingTime start date and time of task
     * @param endTime      end date and time of task
     */
    public Event(String name, String startingTime, String endTime) {
        super(name);
        startingPeriod = startingTime.split(" ");
        endingPeriod = endTime.split(" ");
        if (startingPeriod[0].contains("/")) {
            this.startDate = LocalDate.parse(startingPeriod[0].replaceAll("/", "-"));
        } else {
            this.startDate = LocalDate.parse(startingPeriod[0]);
        }

        if (endingPeriod[0].contains("/")) {
            this.endDate = LocalDate.parse(endingPeriod[0].replaceAll("/", "-"));
        } else {
            this.endDate = LocalDate.parse(endingPeriod[0]);
        }

        this.startTime = startingPeriod[1];
        this.endTime = endingPeriod[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDate.getDayOfMonth() + " "
                + startDate.getMonth() + " " + startDate.getYear() + ", " + startTime + " to: "
                + endDate.getDayOfMonth() + " " + endDate.getMonth() + " " + endDate.getYear() + ", " + endTime + " )";
    }
}
