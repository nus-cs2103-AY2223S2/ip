package duke.commands;

import java.util.Arrays;
import java.util.Optional;

public enum CommandInput {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye"),
    UNRECOGNIZED_CMD("");

    private String commandString;

    CommandInput(String commandLine) {
        this.commandString = commandLine;
    }

    public static CommandInput getCommandInput(String commandLine) {
        String commandInput = commandLine.split(" ")[0];
        Optional<CommandInput> command = Arrays.stream(CommandInput.values()).filter(cmd -> cmd.commandString.equals(commandInput)).findFirst();
        return command.isEmpty() ? UNRECOGNIZED_CMD : command.get();
    }

}
