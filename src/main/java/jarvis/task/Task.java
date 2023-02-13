package jarvis.task;

import java.util.Scanner;

import jarvis.duration.Duration;
import jarvis.exception.command.CommandParseException;
import jarvis.exception.command.MissingParameterException;

/**
 * Abstract task class.
 */
public abstract class Task {
    private boolean isDone;
    private final String description;

    /**
     * Constructor for a task, marked as undone.
     *
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
     *
     * @param description Description of the task.
     * @param isDone Whether the task is marked as done.
     * @throws MissingParameterException If description is null or blank.
     */
    public Task(String description, boolean isDone) throws MissingParameterException {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Serializes a task for storage.
     *
     * @return Serial string.
     */
    public abstract String serialize();

    /**
     * Creates a task from the given serial string.
     *
     * @param serial Serial string.
     * @return Created task.
     */
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
        String duration = null;

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
            case ToDoTask.ID:
                return new ToDoTask(description, isDone);
            case DeadlineTask.ID:
                if (scanner.hasNext()) {
                    deadline = scanner.next();
                }
                return new DeadlineTask(description, deadline, isDone);
            case EventTask.ID:
                if (scanner.hasNext()) {
                    fromDateTime = scanner.next();
                }
                if (scanner.hasNext()) {
                    toDateTime = scanner.next();
                }
                return new EventTask(description, fromDateTime, toDateTime, isDone);
            case TimedTask.ID:
                if (scanner.hasNext()) {
                    duration = scanner.next();
                }
                return new TimedTask(description, Duration.deserialize(duration));
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
     * @param isDone Whether the task is to be marked as done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task satisfies the given filter.
     *
     * @param filter Task filter.
     * @return Whether the task satisfies the given filter.
     */
    public boolean satisfies(TaskFilter filter) {
        if (filter == null || filter.isEmpty() || filter.hasNoKeywords()) {
            return true;
        }

        String lowerDescription = this.description.toLowerCase();
        for (String keyword: filter.getKeywords()) {
            if (lowerDescription.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
