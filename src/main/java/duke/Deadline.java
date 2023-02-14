package duke;

public class Deadline extends Task {
    private DateTimeHandler deadline;

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
        this.deadline = new DateTimeHandler(deadline);
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
        this.deadline = new DateTimeHandler(deadline);
    }

    /**
     * Formats the Deadline task to be printed by Duke.
     * @return The formatted Deadline task as a String.
     */
    @Override
    public String printTask() {
        return String.format("[D][%s] %s (by: %s)",
                (isDone() ? "X" : " "),
                getDescription(),
                deadline.formatPrint());
    }
    
    
    /**
     * Formats the Deadline task to be saved in a format recognisable by Storage.
     * @return The formatted Deadline task as a String.
     */
    @Override
    public String formatTask() {
        return String.format("deadline~-~-~%s~-~-~%s~-~-~%s",
                getDescription(),
                deadline.formatSave(),
                isDone() ? "X" : "O");
    }
}
