package duke.tasks;

/**
 * Inherits abstract class Task. Characterised by description only.
 *
 * @author jengoc415
 */
public class Todo extends Task {
    /**
     * Constructor for task type: todo
     *
     * @param instruction full instruction keyed in by user
     */
    public Todo(String instruction) {
        super(instruction);
        this.description = instruction.substring(5);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.toString(), description);
    }
}
