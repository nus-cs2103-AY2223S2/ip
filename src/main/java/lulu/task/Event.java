package lulu.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task. An Event has additional attributes, from and to, to indicate when
 * the task starts and ends.
 * It has an alternative way to display the starting and ending, utilising the Java LocalDate class.
 */
public class Event extends Task {
    final int NUMBER_HYPHENS_IN_DATE = 3;
    private LocalDate fromDate;
    private String from;
    private LocalDate toDate;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            if (from.split("-").length == NUMBER_HYPHENS_IN_DATE) {
                this.fromDate = LocalDate.parse(from.substring(1, 11));
            } else {
                this.from = from;
            }
            if (to.split("-").length == NUMBER_HYPHENS_IN_DATE) {
                this.toDate = LocalDate.parse(to.substring(1, 11));
            } else {
                this.to = to;
            }
        } catch (DateTimeParseException e) {
            System.out.println("INVALID DATE FORMAT, please use xxxx-xx-xx");
        }
    }

    /**
     * @return a String representation of an Event
     */
    @Override
    public String toString() {
        String s1, s2;
        if (fromDate == null) {
            s1 = from;
        } else {
            s1 = fromDate.toString();
        }
        if (toDate == null) {
            s2 = to;
        } else {
            s2 = toDate.toString();
        }
        return ("[E]" + super.toString() + "(from: " + s1 + " to: " + s2 + ")");
    }

    /**
     * This method is used to convert an Event's task description and details to a String
     * to be written to a save file.
     *
     * @return a String to be written to a save file.
     */
    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        String s1, s2;
        if (fromDate == null) {
            s1 = from;
        } else {
            s1 = fromDate.toString();
            // space necessary for loading data the next time
            s1 = ' ' + s1;
        }
        if (toDate == null) {
            s2 = to;
        } else {
            s2 = toDate.toString();
            // space necessary for loading data the next time
            s2 = ' ' + s2;
        }
        return ("E`" + i + "`" + this.description + "`" + s1 + "`" + s2 + '\n');
    }

    /**
     * This method is used to update the description of an Event.
     *
     * @param text the new description of the Event
     */
    @Override
    public void update(String text) {
        String[] updateInformation = text.split(" ");
        String update = updateInformation[0].toUpperCase();
        switch (update) {
        case "DESCRIPTION":
            this.description = updateInformation[1];
            break;
        case "FROM":
            this.from = updateInformation[1];
            break;
        case "TO":
            this.to = updateInformation[1];
            break;
        }
    }
}
