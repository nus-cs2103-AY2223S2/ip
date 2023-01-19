package jarvis.task;

import jarvis.exception.CommandParseException;
import jarvis.exception.MissingParameterException;

import java.util.Scanner;

public abstract class Task {

    /**
     * Serializes a task for storage.
     * @return Serial string.
     */
    public abstract String serialize();

    private boolean isDone;
    private final String description;

    /**
     * Constructor for a task, marked as undone.
     * @param description Description of the task.
     * @throws MissingParameterException If description is null or blank.
     */
    public Task(String description) throws MissingParameterException {
        if (description == null || description.isBlank()) {
            throw new MissingParameterException("Missing description", "A task description is needed.");
        }

        this.isDone = false;
        this.description = description;
    }

    /**
     * Constructor for a task.
     * @param description Description of the task.
     * @param isDone Whether the task is marked as done.
     * @throws MissingParameterException If description is null or blank.
     */
    public Task(String description, boolean isDone) throws MissingParameterException {
        this(description);
        this.isDone = isDone;
    }

    public static Task deserialize(String serial) {
        if (serial == null || serial.isBlank()) {
            return null;
        }

        Scanner scanner = new Scanner(serial).useDelimiter("\\s*/\\s*");
        String type = "";
        boolean isDone = false;
        String description = null;
        String deadline = null;
        String fromDateTime = null;
        String toDateTime = null;

        if (scanner.hasNext()) {
            type = scanner.next();
        }
        if (scanner.hasNextBoolean()) {
            isDone = scanner.nextBoolean();
        }
        if (scanner.hasNext()) {
            description = scanner.next();
        }

        try {
            switch (type) {
            case "T":
                return new ToDoTask(description, isDone);
            case "D":
                if (scanner.hasNext()) {
                    deadline = scanner.next();
                }
                return new DeadlineTask(description, deadline, isDone);
            case "E":
                if (scanner.hasNext()) {
                    fromDateTime = scanner.next();
                }
                if (scanner.hasNext()) {
                    toDateTime = scanner.next();
                }
                return new EventTask(description, fromDateTime, toDateTime, isDone);
            default:
                return null;
            }
        } catch (CommandParseException e) {
            return null;
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Setter of isDone.
     * @param isDone Whether the task is to be marked as done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter of description.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task satisfies the given filter.
     * @param filter Task filter.
     * @return Whether the task satisfies the given filter.
     */
    public boolean satisfies(TaskFilter filter) {
        if (filter == null || filter.isEmpty() || filter.hasNoKeywords()) return true;

        String lowerDescription = this.description.toLowerCase();
        for (String keyword: filter.getKeywords()) {
            if (lowerDescription.contains(keyword)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
