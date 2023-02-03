package book.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract class representing a {@code Task}.
 */
public abstract class Task implements Comparable<Task> {
    /** {@code DateTimeFormatter} storing the format for printing {@code LocalDateTime}. */
    protected static DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd MMM '('EEE')' - hh:mma");
    /** {@code DateTimeFormatter} storing the format for saving {@code LocalDateTime}. */
    protected static DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
    /** {@code String} description of the {@code Task} */
    protected String description;
    /** {@code boolean} representing the completion status of the {@code Task}. */
    protected boolean isDone;

    /**
     * Initializes a {@code Task} object.
     *
     * @param description {@code String} description of the {@code Task} object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes the status of a {@code Task} object to complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the status of a {@code Task} object to incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the {@code String} representation of a {@code Task} for saving.
     *
     * @return {@code String} representation of a {@code Task} for saving.
     */
    public abstract String saveString();

    /**
     * Returns {@code true} if the {@code String} description contains {@code String} keyword, else
     * return {@code false}.
     *
     * @param keyword {@code String} keyword to check is in {@code String} description.
     * @return {@code true} if the {@code String} description contains {@code String} keyword, else
     *         return {@code false}.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Compares two {@code Task} instances lexicographically, based on their {@code String}
     * description.
     *
     * @param task the {@code Task} object to be compared.
     * @return {@code int 0} if the {@code String} descriptions are equal, an {@code int} value
     *         less than {@code 0} if the {@code String} description is lexicographically less than
     *         the {@code String} description of the {@code Task} argument, and an {@code int}
     *         value greater than {@code 0} if the description is lexicographically greater than
     *         the {@code String} description of the {@code Task} argument.
     */
    @Override
    public int compareTo(Task task) {
        return this.description.compareTo(task.description);
    }

    /**
     * Returns the {@code String} representation of the {@code Task}.
     *
     * @return {@code String} representation of the {@code Task}.
     */
    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return "[" + done + "] " + description;
    }
}
