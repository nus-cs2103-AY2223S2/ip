package duke.commands;

import java.util.Arrays;
import java.util.Optional;

import duke.parsing.Parser;
import duke.tasks.TaskList;

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
    FIND("find"),
    VIEWSCHED("view"),
    UNRECOGNIZED_CMD("");

    private String commandString;

    private CommandInput(String commandLine) {
        this.commandString = commandLine;
    }

    /**
     * Gets the type of command that the user entered.
     *
     * @param commandLine command line input from user
     * @return commandInput type of corresponding command
     */
    private static CommandInput getCommandInput(String commandLine) {
        String commandInput = Parser.parseCommandInput(commandLine);
        Optional<CommandInput> command = Arrays.stream(CommandInput.values()).filter(
                cmd -> cmd.commandString.equals(commandInput)).findFirst();
        return command.isEmpty() ? UNRECOGNIZED_CMD : command.get();
    }

    /**
     * From a given string command input from the user,
     * get its corresponding "Command" object.
     *
     * @param commandLine command input from user
     * @param tasks task list to perform command on
     * @return corresponding Command object
     */
    public static Command getCommand(String commandLine, TaskList tasks) {
        CommandInput commandInput = getCommandInput(commandLine);
        switch (commandInput) {
        case LIST:
            return new ListCmd(tasks, commandLine);
        case MARK:
            return new MarkCmd(tasks, commandLine);
        case UNMARK:
            return new UnmarkCmd(tasks, commandLine);
        case DELETE:
            return new DeleteCmd(tasks, commandLine);
        case EVENT:
            return new EventCmd(tasks, commandLine);
        case DEADLINE:
            return new DeadlineCmd(tasks, commandLine);
        case TODO:
            return new ToDoCmd(tasks, commandLine);
        case BYE:
            return new ByeCmd(tasks, commandLine);
        case FIND:
            return new FindCmd(tasks, commandLine);
        case VIEWSCHED:
            return new ViewSchedCmd(tasks, commandLine);
        default:
            return new UnrecognizedCmd(tasks, commandLine);
        }
    }
}
