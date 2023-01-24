package duke.tasks;

import java.time.LocalDateTime;

public class Todo extends Task {
    public Todo(String taskText) {
        super(taskText);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String writeFile() {
        return String.format("T|%s|%s", this.getCurrentStatus(), this.getTaskText());
    }

    public LocalDateTime getDate() {
        return null;
    }

    public String getTaskType() {
        return "T";
    }
}
