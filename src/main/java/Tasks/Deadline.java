package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parsing.ParsedDate;

/**
 * This class represents a Deadline Task to be done, with a deadline
 */
public class Deadline extends Task {
    private ParsedDate endDate;

    protected Deadline(String name, LocalDateTime endDate) throws NoTaskDescriptionException{
        super(name, "Deadline");
        this.endDate = new ParsedDate(endDate);
    }

    /**
     * Check whether the task is due on the date
     * 
     * @param date date to compare endDate to
     */
    public boolean isEqualDate(LocalDate date) {
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
    protected String stringifyTaskToSave() {
        return "D|" + super.stringifyTaskToSave() + "|" + this.endDate;
    }
}
