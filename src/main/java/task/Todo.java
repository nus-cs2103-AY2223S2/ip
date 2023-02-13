package task;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Todo task
 */
public class Todo extends Task {
    private Todo(String desc, boolean done) {
        super(desc, done);
    }

    /**
     * @param desc Task description
     */
    public Todo(String desc) {
        this(desc, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean hasDate(LocalDate date) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Todo markDone() {
        return new Todo(this.desc, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Todo markNotDone() {
        return new Todo(this.desc, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<LocalDate> getDate() {
        return Optional.empty();
    }
}
