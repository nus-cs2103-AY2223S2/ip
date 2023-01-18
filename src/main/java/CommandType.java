public enum CommandType {
    EXIT("bye", false),
    DISPLAY_LIST("list", false),
    MARK_TASK_AS_DONE("mark", true),
    MARK_TASK_AS_UNDONE("unmark", true),
    TODO("todo", true),
    DEADLINE("deadline", true),
    EVENT("event", true),
    DELETE("delete", true);

    private final String command;
    private final boolean hasArguments;

    private CommandType(String command, boolean hasArguments) {
        this.command = command;
        this.hasArguments = hasArguments;
    }

    public String getCommand() {
        return command;
    }

    public boolean hasArguments() {
        return hasArguments;
    }

    public static CommandType getCommandType(String command) throws DukeException {
        CommandType[] values = values();
        for (CommandType value : values) {
            if (command.equals(value.command)) {
                return value;
            }
        }
        throw new InvalidCommandDukeException();
    }
}
