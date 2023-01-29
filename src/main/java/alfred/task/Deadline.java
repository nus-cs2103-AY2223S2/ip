package alfred.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, format);
    }

    @Override
    public boolean containsDate(LocalDate date) {
        return deadline.toLocalDate().isEqual(date);
    }
    @Override
    public String addToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String str = String.format("D | %d | %s | %s",
                isDone ? 1 : 0, this.description, this.deadline.format(formatter));
        return str + "\n";
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return String.format("[D][%s] %s(by: %s)",
                this.isDone ? "X" : " ", this.description,
                this.deadline.format(formatter));
    }
}
