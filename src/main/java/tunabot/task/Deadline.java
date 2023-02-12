package tunabot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tunabot.exceptions.InputException;

/**
 * Class to handle Deadline objects
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Initializes new Deadline with the given name and deadline
     * @param name Name of Deadline
     * @param deadline Deadline of Deadline
     */
    public Deadline(String name, String deadline) throws InputException {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }

    /**
     * Initializes new Deadline with the given name, status and deadline
     * @param name Name of Deadline
     * @param isDone Status of Deadline
     * @param deadline Deadline of Deadline
     */
    public Deadline(String name, String isDone, String deadline) throws InputException {
        super(name, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }

    public void setDeadline(String deadline) throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }
    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return deadline.format(formatter);
    }
    @Override
    public String saveFormat() {
        return "T;" + this.getName() + ";" + this.getDone() + ";" + this.getDeadline();
    }
    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return "[D]" + box + this.getName() + " (by " + this.getDeadline() + ")";
    }
}
