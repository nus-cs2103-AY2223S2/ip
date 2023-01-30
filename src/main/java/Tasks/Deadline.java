package tasks;

import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;

/**
 * This class represents a Deadline Task to be done, with a deadline
 */
public class Deadline extends Task {
    private LocalDateTime endDate;

    protected Deadline(String name, LocalDateTime endDate) throws NoTaskDescriptionException{
        super(name, "Deadline");
        this.endDate = endDate;
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
