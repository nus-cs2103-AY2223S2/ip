package duke.tasks;

/**
 * Inherits abstract class Task. Characterised by description, start and end time.
 *
 * @author jengoc415
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for task type: event
     *
     * @param instruction full instruction keyed in by user
     */
    public Event(String instruction) {
        super(instruction);
        this.modifiedInstr = instruction.substring(6);
        this.description = modifiedInstr.split(" /")[0];
        this.start = modifiedInstr.split(" /")[1].substring(5);
        this.end = modifiedInstr.split(" /")[2].substring(3);
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)",
                super.toString(),
                description,
                this.start,
                this.end
        );
    }
}
