package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import util.DukeException;

/**
 * The Deadline class extends the Task class and represents a task with a due date.
 *
 * @author @tricixg
 * @version 1.0
 */
public class Deadline extends Task {

    protected String by;
    /**
     * Constructs a new Deadline object with the given task and due date.
     *
     * @param description a string description of the task.
     * @param by The task's due date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    /**
     * Constructs a new Deadline object with the given task and due date.
     *
     * @param description a string description of the task.
     * @param by The task's due date as a LocalDate type
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
    }

    /**
     * Constructs a new Deadline object with the given task and due date.
     *
     * @param isDone a boolean indicating whether the task is completed or not.
     * @param description a string description of the task.
     * @param by The task's due date.
     */
    public Deadline(Boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns a string representation of the task.
     * in the format "[D][status icon] task description (by: due date)".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Getter method to access the date of the deadline.
     * @return date as a String.
     */
    public String getDate() {
        return by;
    }

    /**
     * Creates a new Deadline task from a user's input
     *
     * @param array a list of tasks.
     * @param splitInput an array of strings containing the user input.
     */
    public static String createDeadlineTask(ArrayList<Task> array, String[] splitInput) {
        if (splitInput.length == 1 || splitInput[1].equals("")) {
            try {
                throw new DukeException("deadline");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            for (int j = 1; j < splitInput.length; j++) {
                if (splitInput[j].equals("/by")) {
                    for (int k = 1; k < j - 1; k++) {
                        splitInput[1] = splitInput[1] + " " + splitInput[k + 1];
                    }
                    for (int l = splitInput.length - 1; l > j + 1; l--) {
                        splitInput[splitInput.length - 1] = splitInput[l - 1] + " " + splitInput[splitInput.length - 1];
                    }
                } else {
                    splitInput[j] = splitInput[j];
                }
            }
            String date = splitInput[splitInput.length - 1];
            String desc = splitInput[1];
            if (isDate(date)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate ld = LocalDate.parse(date, formatter);
                Deadline d = new Deadline(desc, ld);
                array.add(d);
                return Ui.addTask(array, d);
            } else {
                Deadline d = new Deadline(desc, date);
                array.add(d);
                return Ui.addTask(array, d);
            }
        }
        return "error";
    }
    /**
     * Checks whether a string is an instance of LocalDate
     * in the format "yyyy-MM-dd"
     *
     * @param date String to be checked
     * @return True if "yyyy-MM-dd"
     */
    public static boolean isDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = null;
        try {
            ld = LocalDate.parse(date, formatter);
            System.out.println(ld);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the task
     * in the format "D | completion status | task description | due date".
     *
     * @return String of deadline task in save format
     */
    @Override
    public String saveFormat() {
        String d = " | ";
        int marked = this.getIsDone() ? 1 : 0;
        return "D" + d + marked + d + description + d + by;
    }
}
