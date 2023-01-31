package task;

import java.time.LocalDate;

public class Todo extends Task {
    private Todo(String desc, boolean done) {
        super(desc, done);
    }

    public Todo(String desc) {
        this(desc, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean hasDate(LocalDate date) {
        return false;
    }

    @Override
    public Todo markDone() {
        return new Todo(this.desc, true);
    }

    @Override
    public Todo markNotDone() {
        return new Todo(this.desc, false);
    }
}
