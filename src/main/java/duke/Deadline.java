package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the given description, completion status and deadline.
     * The deadline given has to be in either formats below.
     * dd/MM/yyyy HHmm
     * HHmm
     * dd/MM/yyyy 
     * @param description The description of the Deadline task.
     * @param isDone The done status of the Deadline task.
     * @param deadline The deadline of the Deadline task as a String.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = DateTimeParser.parseInput(deadline);
    }

    /**
     * Constructs a Deadline task with the given description and deadline.
     * The deadline given has to be in either formats below.
     * The isDone status defaults to false.
     * @param description The description of the Deadline task.
     * @param deadline The deadline of the Deadline task as a String.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateTimeParser.parseInput(deadline);
    }

    /**
     * Formats the Deadline task to be printed by Duke.
     * @return The formatted Deadline task as a String.
     */
    @Override
    public String printTask() {
        return String.format("[D][%s] %s (by: %s)",
                (super.isDone() ? "X" : " "),
                super.getDescription(),
                DateTimeParser.formatOutput(this.deadline));
    }
    
    
    /**
     * Formats the Deadline task to be saved in a format recognisable by Storage.
     * @return The formatted Deadline task as a String.
     */
    @Override
    public String formatTask() {
        return String.format("deadline~-~-~%s~-~-~%s~-~-~%s",
                this.getDescription(),
                DateTimeParser.formatSave(this.deadline),
                this.isDone() ? "X" : "O");
    }
}
