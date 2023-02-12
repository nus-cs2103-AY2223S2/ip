package book.task;

/**
 * Subclass of {@code Task} representing an {@code ToDo}.
 */
public class ToDo extends Task {
    /**
     * Initializes an {@code ToDo} object.
     *
     * @param description {@code String} description of the {@code ToDo} object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the {@code String} representation of a {@code ToDo} for saving.
     *
     * @return {@code String} representation of a {@code ToDo} for saving.
     */
    @Override
    public String saveString() {
        return "T;" + this.isDone + ";" + this.description;
    }

    /**
     * Returns the {@code String} representation of the {@code ToDo}.
     *
     * @return {@code String} representation of the {@code ToDo}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
