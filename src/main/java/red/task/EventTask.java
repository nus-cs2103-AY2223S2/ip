package red.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is for tasks that are considered an event with a timeframe.
 */
public class EventTask extends Task {

    protected LocalDate startDate = null;
    protected LocalDate endDate = null;
    protected String formattedStartDate;
    protected String formattedEndDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



    /**
     * Constructor for a EventTask that takes in a description as well as starting and ending time.
     *
     * @param description The description of the EventTask.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.startDate = getLocalDate(start);
        this.formattedStartDate = this.startDate.format(dateTimeFormatter);
        this.endDate = getLocalDate(end);
        this.formattedEndDate = this.endDate.format(dateTimeFormatter);;
    }


    /**
     * Constructor for a EventTask that takes in a description as well as starting and ending time.
     *
     * @param description The description of the EventTask.
     * @param start LocalDate format of the starting time of the event.
     * @param end LocalDate format of the ending time of the event.
     */
    public EventTask(String description, LocalDate start, LocalDate end) {
        super(description);
        this.startDate = start;
        this.formattedStartDate = start.format(dateTimeFormatter);
        this.endDate = end;
        this.formattedEndDate = end.format(dateTimeFormatter);
    }

    /**
     * Parses through String to obtain a LocalDate object
     *
     * @param date String representation of time
     * @return LocalDate Object of specified time
     */
    public LocalDate getLocalDate(String date) {
        String[] dateStr = date.split("/",3);
        if (dateStr.length < 3) {
            throw new RuntimeException("Specification of the EventTask date is incorrect\n");
        }
        return LocalDate.of( Integer.valueOf(dateStr[2]), Integer.valueOf(dateStr[1]), Integer.valueOf(dateStr[0]));

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + formattedStartDate + " To: " + formattedEndDate + ")";
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask checkedObj = (EventTask) obj;
        boolean isSameDescription = this.description.equals(checkedObj.description);
        boolean isSameStart = this.startDate.equals(checkedObj.startDate);
        boolean isSameEnd = this.endDate.equals(checkedObj.endDate);

        if (isSameDescription && isSameStart && isSameEnd) {
            return true;
        }

        return false;
    }


}