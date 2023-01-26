package tunabot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime deadline;

    public Deadline(String name, String deadline) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }
    public Deadline(String name, String isDone, String deadline) {
        super(name, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return deadline.format(formatter);
    }
    @Override
    public String saveFormat() {
        return "T;" + this.name + ";" +this.isDone + ";" + this.getDeadline();
    }
    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return"[D]" + box + this.getName() + "(by " + this.getDeadline() + ")";
    }
}
