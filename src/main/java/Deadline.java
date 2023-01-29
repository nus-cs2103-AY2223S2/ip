import java.time.LocalDate;
public class Deadline extends Task {
    private LocalDate end;
    public Deadline (String name, LocalDate end) {
        super(name);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.end + ")";
    }
}
