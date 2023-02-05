package roody;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task{
    private LocalDate deadline;
    private char type = 'D';

    /**
     * Creates a Deadline with specified description and due date.
     * @param description The description of the deadline.
     * @param date The due date of the deadline.
     */
    public Deadline(String description, LocalDate date){
        super(description);
        this.deadline = date;
    }
    public void setDeadline(LocalDate date) {
        this.deadline = date;
    }
    public LocalDate getDeadline() {
        return this.deadline;
    }
    @Override
    public String saveTask() {
        return super.saveTask() + '|' + this.type + '|' + this.deadline;
    }
    @Override
    public char getType() {
        return type;
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
