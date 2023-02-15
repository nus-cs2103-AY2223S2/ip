package duke.task;

import duke.exception.DukeException;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskDeserializer;
import duke.storage.serializer.TaskSerializer;

/**
 * Represents a task that needs to be done but does not have a specified deadline.
 */
public class Todo extends Task {
    private static final String ICON = "T";

    public Todo(String description, boolean isCompleted) throws DukeException {
        super(description, isCompleted);
    }

    /**
     * Returns whether {@code icon} belongs to a Todo Task.
     *
     * @param icon Icon to be checked.
     * @return whether the {@code icon} belongs to a Todo Task.
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
            return new Todo(description, isCompleted);
        };
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer();
        ts.add(CATEGORY_KEY, ICON);
        ts.add(DESCRIPTION_KEY, description);
        ts.add(COMPLETED_KEY, isCompleted);
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s",
            ICON,
            super.toString());
    }
}
