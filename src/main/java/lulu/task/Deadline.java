package lulu.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task. A deadline has an additional attribute, by, to indicate when the task is
 * supposed to be completed.
 * It has an alternative way to display the deadline, utilising the Java LocalDate class.
 */
public class Deadline extends Task {
    final int NUMBER_HYPHENS_IN_DATE = 3;

    private LocalDate byDate;
    private String by;

    public Deadline(String description, String by) {
        super(description);
        try {
            if (by.split("-").length == NUMBER_HYPHENS_IN_DATE) {
                this.byDate = LocalDate.parse(by.substring(1, 11));
            } else {
                this.by = by;
            }
        } catch (DateTimeParseException e) {
            System.out.println("INVALID DATE FORMAT, please use xxxx-xx-xx");
        }
    }

    /**
     * @return a String representation of a Deadline
     */
    @Override
    public String toString() {
        String s;
        if (byDate == null) {
            s = by;
        } else {
            s = byDate.toString();
        }
        return ("[D]" + super.toString() + "(by: " + s + ")");
    }

    /**
     * This method is used to convert a Deadline's task description and details to a String
     * to be written to a save file.
     *
     * @return a String to be written to a save file.
     */
    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        String s;
        if (byDate == null) {
            s = by;
        } else {
            s = byDate.toString();
            // space necessary for loading data the next time
            s = ' ' + s;
        }
        return ("D`" + i + "`" + this.description + "`" + s + '\n');
    }

    /**
     * This method is used to update the description of a Deadline.
     *
     * @param text the new description of the Deadline
     */
    @Override
    public void update(String text) {
        String[] updateInformation = text.split(" ");
        String update = updateInformation[0].toUpperCase();
        switch (update) {
        case "DESCRIPTION":
            this.description = updateInformation[1];
            break;
        case "BY":
            this.by = updateInformation[1];
            break;
        }
    }
}
