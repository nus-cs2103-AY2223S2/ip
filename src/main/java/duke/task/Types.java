package duke.task;

/**
 * Represents the Types identifier for each task.
 */
public enum Types {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String identifier;

    Types(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
