package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskDeserializer;
import duke.storage.serializer.TaskSerializer;

/**
 * Represents an task to be done after a certain timing.
 */
public class DoAfter extends Task {
    private static final String ICON = "A";
    private static final String AFTER_KEY = "after";

    protected LocalDateTime after;

    /**
     * Initialises an DoAfter task.
     *
     * @param description Description of DoAfter task.
     * @param isCompleted Whether the DoAfter task has been completed.
     * @param after Time to be completed after.
     * @throws DukeException
     */
    public DoAfter(String description, boolean isCompleted, String after) throws DukeException {
        super(description, isCompleted);
        try {
            this.after = LocalDateTime.parse(after, RECEIVE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not parse 'after' as date time");
        }
    }

    /**
     * Returns whether {@code icon} belongs to a Event Task.
     *
     * @param icon Icon to be checked.
     * @return whether the {@code icon} belongs to a Event Task.
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
            String after = serializer.get(AFTER_KEY).toString();
            return new DoAfter(description, isCompleted, after);
        };
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer();
        ts.add(CATEGORY_KEY, ICON);
        ts.add(DESCRIPTION_KEY, description);
        ts.add(COMPLETED_KEY, isCompleted);
        ts.add(AFTER_KEY, after.format(RECEIVE_FORMAT));
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (after: %s)",
            ICON,
            super.toString(),
            after.format(PRINT_FORMAT));
    }
}
