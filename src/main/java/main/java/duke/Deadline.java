package main.java.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected boolean hasTime;

    public Deadline(String description, LocalDateTime by, boolean hasTime) {
        super(description);
        this.by = by;
        this.hasTime = hasTime;
    }

    public String getBy() {
        if (hasTime) {
            return DateTimeParser.dateTimeToDisplayString(this.by);
        } else {
            return DateTimeParser.dateToDisplayString(this.by.toLocalDate());
        }
    }

    public String getByInStorageFormat() {
        if (hasTime) {
            return DateTimeParser.dateTimeToStorageString(this.by);
        } else {
            return DateTimeParser.dateToStorageString(this.by.toLocalDate());
        }
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    public static Deadline parseDeadlineCommand(Scanner stringStream) throws DukeException, DateTimeParseException {
        String taskDesc = "";
        String byString = "";
        boolean foundBy = false;
        while (stringStream.hasNext()) {
            String temp = stringStream.next();
            if (temp.equalsIgnoreCase("/by")) {
                foundBy = true;
                continue;
            }

            if (foundBy) {
                byString += temp + " ";
            } else {
                taskDesc += temp + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (!foundBy || byString.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Deadline tasks require a /by.");
        }

        byString = byString.trim();
        LocalDateTime by = DateTimeParser.parse(byString);

        String[] parts = byString.split(" ");
        boolean hasTime = parts.length == 2;

        Deadline newTask = new Deadline(taskDesc.trim(), by, hasTime);
        return newTask;
    }

    public static Deadline parseDeadlineStringArray(String[] parts) throws DateTimeParseException {
        String taskDesc = parts[2];
        String byString = parts[3];
        String[] byParts = byString.split(" ");
        boolean hasTime = byParts.length == 2;
        Deadline task = new Deadline(taskDesc, DateTimeParser.parse(byString), hasTime);
        if (Integer.parseInt(parts[1]) == 1) {
            task.mark();
        }
        return task;
    }
}
