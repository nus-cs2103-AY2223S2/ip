package colette.task;

/** Enum to represent type of tasks. */
public enum TaskType {
    DEADLINE, TODO, EVENT;

    /**
     * Returns the enum value that matches a command.
     *
     * @param uppercaseCommand Command in uppercase.
     * @return
     */
    public static TaskType matchStringToTaskType(String uppercaseCommand) {
        for (TaskType t : TaskType.values()) {
            if (uppercaseCommand.equals(t.name())) {
                return t;
            }
        }
        assert false;
        return null;
    }
}
