package duke.task;

import duke.exception.DukeException;
import duke.storage.serializer.Deserializer;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskSerializer;

public class Todo extends Task {
    private static final String ICON = "T";
    private static final String DESCRIPTION_KEY = "description";
    private static final String COMPLETED_KEY = "completed";

    public static boolean hasIcon(String s) {
        return s.equals(ICON);
    }

    public static boolean canDeserialize(Serializer serializer) {
        return hasIcon(serializer.get(CATEGORY_KEY).toString());
    }

    public static Deserializer getDeserializer() {
        return (Serializer serializer) -> {
            String description = serializer.get(DESCRIPTION_KEY).toString();
            boolean completed = Boolean.parseBoolean(serializer.get(COMPLETED_KEY).toString());
            return new Todo(description, completed);
        };
    }

    public Todo(String description, boolean completed) throws DukeException {
        super(description, completed);
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer();
        ts.add(CATEGORY_KEY, ICON);
        ts.add(DESCRIPTION_KEY, description);
        ts.add(COMPLETED_KEY, completed);
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", ICON, super.toString());
    }
}
