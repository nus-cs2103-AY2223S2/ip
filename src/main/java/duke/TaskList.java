package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> todos = new ArrayList<>();

    public void add_list(String s) {

    }

    public void delete_list(String s) {

    }


}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

class Todo extends Duke.Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Duke.Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Duke.Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }
}