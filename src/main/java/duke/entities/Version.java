package duke.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Specifies a particular version of the program state.
 */
public class Version {
    private final UUID id = UUID.randomUUID();
    private final ArrayList<Task> state;
    private final LocalDateTime datetime;
    private final String description;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    /**
     * Initiates an instance of the program version.
     *
     * @param state The current state to store.
     * @param datetime The time the changes were made.
     * @param description The description regarding the command performed.
     */
    public Version(ArrayList<Task> state, LocalDateTime datetime, String description) {
        this.state = state;
        this.datetime = datetime;
        this.description = description;
    }

    public UUID getUuid() {
        return id;
    }

    public ArrayList<Task> getState() {
        return state;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    @Override
    public String toString() {
        return description + " on " + formatter.format(getDatetime());
    }
}
