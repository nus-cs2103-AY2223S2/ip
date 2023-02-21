package cluck.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


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

    public static Task buildTaskFromSave(String savedTask) {
        String[] savedTaskFields = savedTask.split("\\|");
        boolean isMarked;

        if (savedTaskFields[1].equals("1")) {
            isMarked = true;
        } else if (savedTaskFields[1].equals("0")) {
            isMarked = false;
        } else {
            System.out.println("Corrupted data found, skipping corrupted data.");
            return null;
        }

        switch (savedTaskFields[0]) {
            case "E":
                return new Event(isMarked, savedTaskFields[2], savedTaskFields[3], savedTaskFields[4]);

            case "D":
                return new Deadline(isMarked, savedTaskFields[2], savedTaskFields[3]);

            case "T":
                return new ToDo(isMarked, savedTaskFields[2]);

            default:
                System.out.println("Corrupted data found, skipping corrupted data.");
                return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return description.equals(task.description) && isMarked.equals(task.isMarked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isMarked);
    }

    @Override
    public String toString() {
        return this.isMarked
                ? String.format("[X] %s", this.description)
                : String.format("[ ] %s", this.description);
    }

}
