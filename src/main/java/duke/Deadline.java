package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = DateTimeParser.parseInput(deadline);
    }
    
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateTimeParser.parseInput(deadline);
    }
    @Override
    public String printTask() {
        return String.format("[D][%s] %s (by: %s)",
                (super.isDone() ? "X" : " "),
                super.getDescription(),
                DateTimeParser.formatOutput(this.deadline));
    }
    
    @Override
    public String formatTask() {
        return String.format("deadline~-~-~%s~-~-~%s~-~-~%s", this.getDescription(), DateTimeParser.formatSave(this.deadline), this.isDone() ? "X" : "O");
    }
}
