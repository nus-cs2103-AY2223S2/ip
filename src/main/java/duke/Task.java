package duke;
import java.io.Serializable;
import java.time.LocalDate;

class Task implements Serializable {
    protected boolean isDone;
    protected String name;
    /**
     * Initialize a Task Based on nameString.
     * @param nameString name of Task
     */
    public Task(String nameString) {
        name = nameString;
    }

    /**
     * Set this.isDone to value of isMarked.
     * @param isMarked to mark or unmark
     */
    public void mark(boolean isMarked) {
        this.isDone = isMarked;
    }

    /**
     * String representation of Task.
     */
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }
}

class Todo extends Task {
    /**
     * initialize todo.
     * @param nameString name of Todo
     */
    public Todo(String nameString) {
        super(nameString);
    }

    /**
     * String representation of todo.
     */
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.name);
    }
}
class Deadline extends Task {
    private LocalDate by;

    /**
     * Initialize deadline.
     * @param nameString name of deadline
     * @param byOption option content
     */
    public Deadline(String nameString, LocalDate byOption) {
        super(nameString);
        by = byOption;
    }

    /**
     * String representation of deadline.
     */
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.name, this.by);
    }
}
class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Initialize event.
     * @param nameString name of event
     * @param fromOption start date
     * @param toOption end date
     */
    public Event(String nameString, LocalDate fromOption, LocalDate toOption) {
        super(nameString);
        from = fromOption;
        to = toOption;
    }

    /**
     * String representation of event.
     */
    public String toString() {
        return String.format("[E][%s] %s (from: %s to %s)", this.isDone ? "X" : " ", this.name, this.from, this.to);
    }
}
