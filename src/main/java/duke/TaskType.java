package duke;

/**
 * The three types of tasks todo, deadline and event.
 */
public enum TaskType {
    TODO, DEADLINE, EVENT;

    /**
     * Takes first letter of todo, deadline and event to represent the corresponding task type.
     * @return The first letter of the corresponding task type.
     */
    @Override
    public String toString() {
        switch (this) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return "";
        }
    }
}
