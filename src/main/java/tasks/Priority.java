package tasks;

/**
 * Represents the priority of a specific task.
 */
public enum Priority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    UNKNOWN("unknown");

    private final String value;

    /**
     * Constructs a new Priority with the specified value.
     *
     * @param value the value of this Priority
     */
    Priority(String value) {
        this.value = value;
    }

    /**
     * Returns whether this Priority's value is equal to the specified input, ignoring case.
     *
     * @param input the input to compare with this Priority's value
     * @return whether this Priority's value is equal to the specified input, ignoring case
     */
    public boolean isEqual(String input) {
        return this.value.equalsIgnoreCase(input);
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns the Priority based on the String input given
     * by iterating through the existing Priority values
     *
     * @return the Priority based on the String input given
     */
    public static Priority getPriority(String input) {
        for (Priority p : Priority.values()) {
            if (p.isEqual(input)) {
                return p;
            }
        }
        return Priority.UNKNOWN;
    }

    public static int getPriorityLevel(Priority priority) {
        switch (priority) {
        case LOW:
            return 3;
        case MEDIUM:
            return 2;
        case HIGH:
            return 1;
        default:
            return 0;
        }
    }

    public static Priority getHigherPriority(Priority priority) {
        switch (priority) {
        case LOW:
            return MEDIUM;
        case MEDIUM:
        case HIGH:
            return HIGH;
        default:
            return LOW;
        }
    }

    public static Priority getLowerPriority(Priority priority) {
        switch (priority) {
        case HIGH:
            return MEDIUM;
        default:
            return LOW;
        }
    }
}
