package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.serializer.Deserializer;
import duke.storage.serializer.Serializer;
import duke.storage.serializer.TaskSerializer;

public class Deadline extends Task {
    private static final String ICON = "D";
    private static final String DESCRIPTION_KEY = "description";
    private static final String COMPLETED_KEY = "completed";
    private static final String BY_KEY = "by";
    private static final DateTimeFormatter RECEIVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-uuuu,EEE,hh:mma");
    
    protected LocalDateTime by;

    public static boolean hasIcon(String icon) {
        return icon.equals(ICON);
    }

    public static Deserializer getDeserializer() {
        return (Serializer serializer) -> {
            String description = serializer.get(DESCRIPTION_KEY).toString();
            boolean completed = Boolean.parseBoolean(serializer.get(COMPLETED_KEY).toString());
            String by = serializer.get(BY_KEY).toString();
            return new Deadline(description, completed, by);
        };
    }

    public Deadline(String description, boolean completed, String by) throws DukeException {
        super(description, completed);
        try {
            this.by = LocalDateTime.parse(by, RECEIVE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not parse 'by' as date time");
        }
    }

    @Override
    public String serialize() {
        Serializer ts = new TaskSerializer(ICON);
        ts.add(DESCRIPTION_KEY, description);
        ts.add(COMPLETED_KEY, completed);
        ts.add(BY_KEY, by.format(RECEIVE_FORMAT));
        return ts.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(PRINT_FORMAT));
    }
}
