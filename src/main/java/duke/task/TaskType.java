package duke.task;

/** Enum to represent type of tasks. */
public enum TaskType {
    DEADLINE, TODO, EVENT;

    /**
     * Returns the enum value that matches a command.
     * @param uppercaseCommand Command in uppercase.
     * @return
     */
    public static TaskType matchStringToTaskType(String uppercaseCommand) {
        if (uppercaseCommand.equals(DEADLINE.name())) {
            return DEADLINE;
        } else if (uppercaseCommand.equals(TODO.name())) {
            return TODO;
        } else if (uppercaseCommand.equals(EVENT.name())) {
            return EVENT;
        } else {
            return null;
        }
    }
}
