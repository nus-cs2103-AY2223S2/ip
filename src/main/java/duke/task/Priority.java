package duke.task;

public enum Priority {
    HIGH("h"),
    MEDIUM("m"),
    LOW("l");

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    /**
     * Returns Priority value from a given char.
     *
     * @param value Given char
     * @return Corresponding Priority value
     */
    public static Priority priorityValue(char value) {
        switch(value) {
            case 'h':
                return Priority.HIGH;
            case 'm':
                return Priority.MEDIUM;
            case 'l':
                return Priority.LOW;
            default:
                return null;
        }
    }

    /**
     * Returns short form of the priority.
     *
     * @return Short form of the priority.
     */
    public String shortString() {
        return value;
    }
}
