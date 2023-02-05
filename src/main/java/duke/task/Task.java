package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyy");
        return date.format(format);
    }

    public String getName() {
        return this.name;
    }

    public String convertFileDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(format);
    }

    public void mark() {
        this.isDone = true;
    }

    
    public void unmark() {
        this.isDone = false;
    }

    public String getFileDesc() {
        return this.isDone 
        ? "1|" + this.name 
        : "0|" + this.name;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}