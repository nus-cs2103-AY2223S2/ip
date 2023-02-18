package task;

import java.time.LocalDate;

/**
 * The `Event` class represents an event task in the to-do list.
 * It extends the `Task` class and contains additional information
 * about the start and end date of the event.
 */
public class Event extends Task {
    static final String TASKSYMBOL = "[E]";
    static final String TASKSAVESYMBOL = "E";
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
     * Returns the Todo task in the format
     * "[TASKSAVESYMBOL | [mark] | [tag] | [task name] | [startDetails] | [endDetails] | [tag name]" where
     * TASKSAVESYMBOL is a static final variable that represents a Event task for saving
     * mark is "1" if the task is marked done, and "0" otherwise.
     * tag is "1" if the task is tagged, and "0" otherwise. If tag is 0, there is no tag name.
     *
     * @return a formatted string for event task that can be saved to a file
     */
    @Override
    public String toSaveFormat() {
        return TASKSAVESYMBOL + super.toSaveFormat() + DIVIDER + startDetails + DIVIDER + endDetails + getSaveTag();
    }

    /**
     * Returns the Todo task as a string in the format
     * "[TASKSYMBOL][status icon][task name][tag name][startDetails][endDetails]" where
     * TASKSYMBOL is a static final variable that represents a event task to display to user
     * status icon is "X" if the task is marked done, and " " otherwise.
     * tag name appears only if there is a tag
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return TASKSYMBOL + super.toString() + "(" + startDetails + " : " + endDetails + ")";
    }
}
