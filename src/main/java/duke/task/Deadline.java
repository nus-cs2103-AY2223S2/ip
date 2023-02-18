package duke.task;

import static duke.utils.FormatHelper.INPUTFORMAT;
import static duke.utils.FormatHelper.PRINTFORMAT;

import java.time.LocalDateTime;



/**
 * Task with a deadline.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     * @param description Description of Task.
     * @param by Date and time to complete the Task by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts Deadline data to save format to be stored.
     * @return Save string format.
     */
    @Override
    public String toSaveFormat() {
        return "D||" + super.toSaveFormat() + "||" + by.format(INPUTFORMAT);
    }

    /**
     * Convert Deadline data to print format.
     * @return Print string format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(PRINTFORMAT) + ")";
    }

    /**
     * Takes string savedData, parse the values and create a Deadline object.
     * @param savedData String input to be decoded.
     * @return Deadline object created from data in savedData.
     */
    public static Deadline fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Deadline generatedDeadline = new Deadline(inputs[2], LocalDateTime.parse(inputs[3], INPUTFORMAT));
        if (inputs[1].equals("1")) {
            generatedDeadline.setCompleted(true);
        }
        return generatedDeadline;
    }

    @Override
    public int compareTo(Deadline otherDeadline) {
        if (this.by.isBefore(otherDeadline.by)) {
            return -1;
        } else if (this.by.isEqual(otherDeadline.by)) {
            return 0;
        } else {
            return 1;
        }
    }
}
