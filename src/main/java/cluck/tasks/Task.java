package cluck.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public abstract class Task {
    protected String description; // name of the task
    protected Boolean isMarked; // whether task is done or not
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public Task(boolean isMarked, String description) {
        this.description = description;
        this.isMarked = isMarked;
    }

    protected static LocalDateTime interpretLocalDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public abstract String makeSaveFormat();

    @Override
    public String toString() {
        return this.isMarked
                ? String.format("[X] %s", this.description)
                : String.format("[ ] %s", this.description);
    }

}
