import java.time.LocalDate;

public class Deadline extends Task {
    private String desc;
    private LocalDate dueDate;

    public Deadline(int id, String description, LocalDate dueDate) {
        super(id);
        desc = description;
        this.dueDate = dueDate;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", description(), dueDate);
    }
}
