import java.util.Arrays;
import java.util.Optional;

public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    UNRECOGNIZED_CMD("");

    private String commandString;

    Command(String commandLine) {
        this.commandString = commandLine;
    }

    public static Command getCommand(String commandLine) {
        String commandInput = commandLine.split(" ")[0];
        Optional<Command> command = Arrays.stream(Command.values()).filter(cmd -> cmd.commandString.equals(commandInput)).findFirst();
        return command.isEmpty() ? UNRECOGNIZED_CMD : command.get();
    }

}
