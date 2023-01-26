package duke.task;

import duke.exception.DukeException;
import duke.storage.serializer.Deserializer;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskSerializer;

public class Todo extends Task {
    private static final String ICON = "T";
    private static final String NAME_KEY = "name";
    private static final String COMPLETED_KEY = "completed";

    public static boolean hasIcon(String s) {
        return s.equals(ICON);
    }

    public static Deserializer getDeserializer() {
        return (Serializer serializer) -> {
            String name = serializer.get(NAME_KEY).toString();
            boolean completed = Boolean.parseBoolean(serializer.get(COMPLETED_KEY).toString());
            return new Todo(name, completed);
        };
    }

    public Todo(String name, boolean completed) throws DukeException {
        super(name, completed);
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer(ICON);
        ts.add(NAME_KEY, name);
        ts.add(COMPLETED_KEY, completed);
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
