package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline object class.
 */
public class Deadline extends Task {

    /** Due date of Deadline */
    protected LocalDate by;

    /**
     * Creates a Deadline object with the description and deadline.
     *
     * @param description details of the Deadline object.
     * @param by          deadline of the Deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new Deadline object based on the user input in the description.
     * The description is split into the task name (input) and the date to be parsed (date).
     *
     * @param taskList the TaskList to add the Deadline object into.
     * @param description     String input for description of a Deadline object.
     *
     * @return String for Deadline creation
     */
    public static String createDeadline(TaskList taskList, String description) {
        String[] inputSplit = description.split("/", 2);
        String input = inputSplit[0];
        String[] dateSplit = inputSplit[1].split(" ", 2);
        String date = dateSplit[1];
        Deadline deadline = new Deadline(input, LocalDate.parse(date));
        taskList.addTask(deadline);
        return Ui.addedTask() + Ui.indent(deadline.toString());
    }

    /**
     * Runs the Deadline creation.
     * Checks the number of tasks in the list and prints it out.
     *
     * @param taskList    the TaskList to add the Deadline object into.
     * @param description String input for description of a Deadline object, with task name and deadline date.
     *
     * @return String for Deadline creation
     */
    public static String runDeadline(TaskList taskList, String description) {
        return createDeadline(taskList, description) + "\n" + Ui.checkList(taskList);
    }

    /**
     * Saved file representation of a Deadline object.
     *
     * @return String format of a Deadline object in saved file form.
     */
    @Override
    public String toSave() {
        return "D | " + super.toSave() + "| " + by;
    }

    /**
     * Represents a Deadline object in the user interface.
     *
     * @return String format of a Deadline object to be displayed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
