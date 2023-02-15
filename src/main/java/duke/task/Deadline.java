package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskDeserializer;
import duke.storage.serializer.TaskSerializer;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    private static final String ICON = "D";
    private static final String BY_KEY = "by";

    protected LocalDateTime by;

    /**
     * Initialises a Deadline task.
     *
     * @param description Description of the task.
     * @param isCompleted Whether the task is completed
     * @param by Due date of task.
     * @throws DukeException
     */
    public Deadline(String description, boolean isCompleted, String by) throws DukeException {
        super(description, isCompleted);
        try {
            this.by = LocalDateTime.parse(by, RECEIVE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not parse 'by' as date time");
        }
    }

    /**
     * Returns whether {@code icon} belongs to a Deadline Task.
     *
     * @param icon Icon to be checked.
     * @return whether the {@code icon} belongs to a Deadline Task.
     */
    public static boolean hasIcon(String icon) {
        return icon.equals(ICON);
    }

    public static boolean canDeserialize(Serializer serializer) {
        return hasIcon(serializer.get(CATEGORY_KEY).toString());
    }

    public static TaskDeserializer getDeserializer() {
        return (TaskSerializer serializer) -> {
            String description = serializer.get(DESCRIPTION_KEY).toString();
            boolean isCompleted = Boolean.parseBoolean(serializer.get(COMPLETED_KEY).toString());
            String by = serializer.get(BY_KEY).toString();
            return new Deadline(description, isCompleted, by);
        };
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer();
        ts.add(CATEGORY_KEY, ICON);
        ts.add(DESCRIPTION_KEY, description);
        ts.add(COMPLETED_KEY, isCompleted);
        ts.add(BY_KEY, by.format(RECEIVE_FORMAT));
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (by: %s)",
            ICON,
            super.toString(),
            by.format(PRINT_FORMAT));
    }
}
