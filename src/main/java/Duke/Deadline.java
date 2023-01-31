package Duke;

/**
 * Class representing a Deadline task with an end time.
 * @author Bryan Juniano
 */
import java.time.LocalDate;
public class Deadline extends Task {
    private String end;
    public Deadline (String name, String end) {
        super(name);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D] | " + super.toString() + " | by: | " + this.end + "";
    }
}
