package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/** A representation of a task with a deadline. */
public class Deadline extends Task {

    /** Deadline of the task. */
    private LocalDate deadline;

    /**
     * Creates a Deadline object with a given name, completion status, and deadline.
     * 
     * @param name     The name of the object to be created
     * @param isDone   The completion status of the object to be created
     * @param deadline A string representation of the task's deadline
     */
    public Deadline(String name, boolean isDone, String deadline) throws InvalidDateException {
        super(name, isDone);
        try {
            this.deadline = Task.parseDate(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getParsedString());
        }
    }

    /**
     * Creates a Deadline object with a given name and deadline.
     * 
     * @param name     The name of the object to be created
     * @param deadline A string representation of the task's deadline
     */
    public Deadline(String name, String deadline) throws InvalidDateException {
        this(name, false, deadline);
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String serialize() {
        String serialized = String.format("%s|%s", super.serialize(), this.deadline);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("%s (by: %s)", super.toString(), Task.formatDate(this.deadline));
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        Deadline task = (Deadline) obj;
        return super.equals(task) && this.deadline.equals(task.deadline);
    }
}
