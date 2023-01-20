package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String name;
    private boolean isCompleted;

    public Task (String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String marking() {
        if (this.isCompleted) {
            return ("[X] ");
        } else {
            return ("[ ] ");
        }
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public String dateFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    public boolean isWithinDate(LocalDateTime date) {
        return false;
    }

    public boolean doesContain(String word) {
        String[] split = this.name.split(" ");
        for (String s : split) {
            String toTest = s.toLowerCase();
            if (toTest.equals(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return marking() + this.name;
    }

    public String toWrite() {
        return (isCompleted ? 1 : 0) + " | " + this.name;
    }
}
