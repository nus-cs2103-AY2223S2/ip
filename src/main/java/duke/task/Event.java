package duke.task;

import static duke.utils.FormatHelper.INPUTFORMAT;
import static duke.utils.FormatHelper.PRINTFORMAT;

import java.time.LocalDateTime;

/**
 * Task with to date and a from date.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor to create an Event
     * @param description Description of the Event.
     * @param from Start date and time of the Event.
     * @param to End date and time of the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts Event data to Save string format.
     * @return Save string format of Event.
     */
    @Override
    public String toSaveFormat() {
        return "E||" + super.toSaveFormat() + "||" + this.from.format(INPUTFORMAT) + "||" + this.to.format(INPUTFORMAT);
    }

    /**
     * Converts Event data to print format.
     * @return Print string format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(PRINTFORMAT) + ", to: " + this.to.format(PRINTFORMAT) + ")";
    }

    /**
     * Takes string savedData, parse the values and create an Event object.
     * @param savedData String input to be decoded.
     * @return Event object created from data in savedData.
     */
    public static Event fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        LocalDateTime parseFrom = LocalDateTime.parse(inputs[3], INPUTFORMAT);
        LocalDateTime parseTo = LocalDateTime.parse(inputs[4], INPUTFORMAT);
        Event generatedEvent = new Event(inputs[2], parseFrom, parseTo);
        if (inputs[1].equals("1")) {
            generatedEvent.setCompleted(true);
        }
        return generatedEvent;
    }
}
