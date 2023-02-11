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
     * @param description     String input for the description of an Event object.
     *
     * @return String for Event creation
     */
    public static String createEvent(TaskList taskList, String description) {
        String[] inputSplit = description.split("/", 2);
        String input = inputSplit[0];
        String[] dateSplit = inputSplit[1].split(" ", 2);
        String date = dateSplit[1];

        Event event = new Event(input, LocalDate.parse(date));
        taskList.addTask(event);
        return Ui.addedTask() + Ui.indent(event.toString());
    }

    /**
     * Runs the Event creation
     * Checks the number of tasks in the list and prints it out.
     *
     * @param taskList    the TaskList to add the Event object into.
     * @param description String input for description of an Event object, with task name and event date.
     *
     * @return String for Event creation
     */
    public static String runEvent(TaskList taskList, String description) {
        return createEvent(taskList, description) + "\n" + Ui.checkList(taskList);
    }

    /**
     * Runs the Event read.
     *
     * @param s The array of strings to be read.
     *
     * @return String for Event creation.
     */
    public static Task readEvent(String[] s) {
        String eventName = s[2].substring(1);
        String[] eventDescription = s[3].substring(1).split(" ");
        String eventDate = eventDescription[0];
        Event event = new Event(eventName, LocalDate.parse(eventDate));
        if (s[1].charAt(1) == '1') {
            event.toMark(true);
        }
        return event;
    }

    /**
     * Represents the saved file format of an Event object.
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
