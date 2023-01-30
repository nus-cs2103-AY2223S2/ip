package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parsing.ParsedDate;

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

    @Override
    public String toString() {
        return "[D] " + this.TasktoString() + " ( by: " + this.endDate + " )";
    }
}
