package Duke.Tasks;

/**
 * Class representing a Deadline task with an end time.
 * @author Bryan Juniano
 */
import Duke.Tasks.Task;

public class Deadline extends Task {
    private String end;
    /**
     * Constructor for ToDo task.
     * @param name name of the Deadline task.
     * @param end end time of the Deadline task.
     */
    public Deadline (String name, String end) {
        super(name);
        this.end = end;
    }

    /**
     * Generates the string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " by: " + this.end + "";
    }
}
