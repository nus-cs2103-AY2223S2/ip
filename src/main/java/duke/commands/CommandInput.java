package duke.commands;

import java.util.Arrays;
import java.util.Optional;

import duke.Parser;

/**
 * Collection of acceptable commands that the user is allowed to enter.
 */
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

    private CommandInput(String commandLine) {
        this.commandString = commandLine;
    }

    /**
     * Gets the type of command that the user entered.
     * @param commandLine command line input from user
     * @return CommandInput type of corresponding command
     */
    public static CommandInput getCommandInput(String commandLine) {
        String commandInput = Parser.parseCommandInput(commandLine);
        Optional<CommandInput> command = Arrays.stream(CommandInput.values()).filter(
            cmd -> cmd.commandString.equals(commandInput)).findFirst();
        return command.isEmpty() ? UNRECOGNIZED_CMD : command.get();
    }

}
