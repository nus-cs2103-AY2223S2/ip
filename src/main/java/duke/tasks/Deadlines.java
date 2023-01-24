package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime time;
    public Deadlines(String taskText, LocalDateTime time) {
        super(taskText);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }

    public String writeFile() {
        return String.format("D|%s|%s|%s", this.getCurrentStatus(), this.getTaskText(),
                this.time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    public LocalDateTime getDate() {
        return this.time;
    }

    public String getTaskType() {
        return "D";
    }
}
