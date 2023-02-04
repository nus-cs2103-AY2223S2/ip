import java.time.LocalDate;

class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String name, String start) {
        super(name);
        this.deadline = LocalDate.parse(start);
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[D][X] " + this.name + " (by: " + getDate(this.deadline) + ")"
                : "[D][ ] " + this.name + " (by: " + getDate(this.deadline) + ")";
    }
}