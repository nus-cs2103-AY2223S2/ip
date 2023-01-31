package alfred.task;
/**
 * Represents a to-do task given by the user.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do object that represents a unique task given by the user.
     * @param description {@inheritDoc}
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToFile() {
        String str = String.format("T | %d | %s",
                isDone ? 1 : 0, this.description);
        return str + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}
