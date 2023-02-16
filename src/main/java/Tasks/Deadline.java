package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parser.ParsedDate;

/**
 * This class represents a Deadline Task to be done, with a deadline
 */
public class Deadline extends Task {
    private ParsedDate endDate;

    public Deadline(String name, LocalDateTime endDate) throws NoTaskDescriptionException{
        super(name, "Deadline");
        this.endDate = new ParsedDate(endDate);
    }

    /**
     * Check whether the task is due on the date
     * 
     * @param date date to compare endDate to
     */
    public boolean contains(LocalDate date) {
        return this.endDate.isEqualDate(date);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " ( by: " + this.endDate + " )";
    }

    @Override
    public String stringifyTaskToSave() {
        return "DEADLINE|" + super.stringifyTaskToSave() + "|" + this.endDate;
    }
}
