package duke;
import java.util.Arrays;
import java.util.Optional;

enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list");

    private String commandString;

    Command(String commandLine) {
        this.commandString = commandLine;
    }

    public static Optional<Command> getCommand(String commandLine) {
        String commandInput = commandLine.split(" ")[0];
        return Arrays.stream(Command.values()).filter(cmd -> cmd.commandString.equals(commandInput)).findFirst();
    }

}
