package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event object class.
 */
public class Event extends Task {

    /** Date of Event */
    protected LocalDate at;

    /**
     * Creates an Event object with the description and event date.
     *
     * @param description details of the Event object.
     * @param at          date of the Event task.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates a new Event object based on the user input in the description.
     * The description is split into the task name (input) and the date to be parsed (date).
     *
     * @param taskList the TaskList to add the Event object into.
     * @param desc     String input for the description of an Event object.
     */
    public static void createEvent(TaskList taskList, String desc) {
        Ui.addedTask();
        String[] inputSplit = desc.split("/", 2);
        String input = inputSplit[0];
        String[] dateSplit = inputSplit[1].split(" ", 2);
        String date = dateSplit[1];
        Event event = new Event(input, LocalDate.parse(date));
        taskList.addTask(event);
        Ui.indent("" + event);
    }

    /**
     * Runs the Event creation
     * Checks the number of tasks in the list and prints it out.
     *
     * @param taskList    the TaskList to add the Event object into.
     * @param description String input for description of an Event object, with task name and event date.
     */
    public static void runEvent(TaskList taskList, String description) {
        createEvent(taskList, description);
        Ui.checkList(taskList);
    }

    /**
     * Saved file representation of an Event object.
     *
     * @return String format of an Event object in saved file form.
     */
    @Override public String toSave() {
        return "E | " + super.toSave() + "| " + at;
    }

    /**
     * Represents an Event object in the user interface.
     *
     * @return String format of an Event object to be displayed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
