package duke;

import java.time.LocalDateTime;

class Deadline extends Task {
    private final LocalDateTime byTime;

    public Deadline(String taskDescription, LocalDateTime byTime) {
        setTaskDescription(taskDescription);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s by: %s", super.toString(), byTime.format(getFormatter()));
    }
}
