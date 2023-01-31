package duke;

import java.time.format.DateTimeFormatter;
abstract class Task {
    private String taskDescription;
    private Boolean isDone = false;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

    void mark() {
        isDone = true;
    }

    void unmark() {
        isDone = false;
    }

    void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    String getTaskDescription() {
        return taskDescription;
    }

    DateTimeFormatter getFormatter() {
        return formatter;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', getTaskDescription());
    }
}
