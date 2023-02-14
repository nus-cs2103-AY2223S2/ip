package task;

import java.time.LocalDate;

/**
 * The `Event` class represents an event task in the to-do list.
 * It extends the `Task` class and contains additional information
 * about the start and end date of the event.
 */
public class Event extends Task {
    private LocalDate startDetails;
    private LocalDate endDetails;

    /**
     * Constructor that creates an instance of the `Event` class
     *
     * @param name the name of the event task
     * @param startDetails the start date of the event task
     * @param endDetails the end date of the event task
     */
    public Event(String name, LocalDate startDetails, LocalDate endDetails) {
        super(name);
        this.startDetails = startDetails;
        this.endDetails = endDetails;
    }

    /**
     * Overrides the `toSaveFormat` method from the `Task` class to return
     * a formatted string that contains the type of the task, its name,
     * start date and end date. The format of the string is as follows:
     * "E" + divider + markToInt + divider + name + divider + startDetails + divider + endDetails;
     *
     * @return a formatted string that can be saved to a file
     */
    @Override
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + DIVIDER + startDetails + DIVIDER + endDetails;
    }

    /**
     * Overrides the `toString` method from the `Task` class to return
     * a string representation of the event task. The format of the string is as follows:
     * "[E]" + super.toString() + "(" + startDetails + " : " + endDetails + ")"
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDetails + " : " + endDetails + ")";
    }


}
