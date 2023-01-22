package book.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected static DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd MMM '('EEE')' - hh:mma");
    protected static DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String saveString();

    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return "[" + done + "] " + description;
    }
}