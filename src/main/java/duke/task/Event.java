package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.serializer.Deserializer;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskSerializer;

public class Event extends Task {
    private static final String ICON = "E";
    private static final String NAME_KEY = "name";
    private static final String COMPLETED_KEY = "completed";
    private static final String FROM_KEY = "from";
    private static final String TO_KEY = "to";
    private static final DateTimeFormatter RECEIVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-uuuu,EEE,hh:mma");

    protected LocalDateTime from;
    protected LocalDateTime to;

    public static boolean hasIcon(String s) {
        return s.equals(ICON);
    }

    public static Deserializer getDeserializer() {
        return (Serializer serializer) -> {
            String name = serializer.get(NAME_KEY).toString();
            boolean completed = Boolean.parseBoolean(serializer.get(COMPLETED_KEY).toString());
            String from = serializer.get(FROM_KEY).toString();
            String to = serializer.get(TO_KEY).toString();
            return new Event(name, completed, from, to);
        };
    }

    public Event(String name, boolean completed, String from, String to) throws DukeException {
        super(name, completed);
        try {
            this.from = LocalDateTime.parse(from, RECEIVE_FORMAT);
            this.to = LocalDateTime.parse(to, RECEIVE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not parse time");
        }
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer(ICON);
        ts.add(NAME_KEY, name);
        ts.add(COMPLETED_KEY, completed);
        ts.add(FROM_KEY, from.format(RECEIVE_FORMAT));
        ts.add(TO_KEY, to.format(RECEIVE_FORMAT));
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(PRINT_FORMAT),
                to.format(PRINT_FORMAT));
    }
}
