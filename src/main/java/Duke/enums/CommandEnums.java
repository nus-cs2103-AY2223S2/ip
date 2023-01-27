package Duke.enums;

public enum CommandEnums {
    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DATE("date"),
    DELETE("delete");

    private final String command;

    CommandEnums(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
