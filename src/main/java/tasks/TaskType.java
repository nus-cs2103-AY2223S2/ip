package tasks;

/**
 * Represents the possible types of tasks that Sirius can add to the tasklist.
 */
public enum TaskType {
    DEFAULT("default"),
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String value;

    /**
     * Constructs a new TaskType with the specified value.
     *
     * @param value the value of this CommandType
     */
    TaskType(String value) {
        this.value = value;
    }

    /**
     * Returns whether this TaskType's value is equal to the specified input, ignoring case.
     *
     * @param input the input to compare with this TaskType's value
     * @return whether this TaskType's value is equal to the specified input, ignoring case
     */
    public boolean isEqual(String input) {
        return this.value.equalsIgnoreCase(input);
    }

    public String getValue() {
        return value;
    }
}
