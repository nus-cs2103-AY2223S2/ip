package duke.commands;

import java.util.Arrays;
import java.util.Optional;

import duke.Parser;
import duke.Ui;
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
    UNRECOGNIZED_CMD("");

    private String commandString;

    private CommandInput(String commandLine) {
        this.commandString = commandLine;
    }

    /**
     * Gets the type of command that the user entered.
     * @param commandLine Command line input from user
     * @return CommandInput type of corresponding command
     */
    private static CommandInput getCommandInput(String commandLine) {
        String commandInput = Parser.parseCommandInput(commandLine);
        Optional<CommandInput> command = Arrays.stream(CommandInput.values()).filter(
                cmd -> cmd.commandString.equals(commandInput)).findFirst();
        return command.isEmpty() ? UNRECOGNIZED_CMD : command.get();
    }
    
    public static Command getCommandFromInput(String commandLine, TaskList tasks) {
        CommandInput commandInput = getCommandInput(commandLine);
        switch (commandInput) {
            case LIST:
                return new ListCmd(tasks, Ui.line);
            case MARK:  
                return new MarkCmd(tasks, Ui.line);
            case UNMARK:
                return new UnmarkCmd(tasks, Ui.line);
            case DELETE:
                return new DeleteCmd(tasks, Ui.line);
            case EVENT:
                return new EventCmd(tasks, Ui.line);
            case DEADLINE:
                return new DeadlineCmd(tasks, Ui.line);
            case TODO:
                return new ToDoCmd(tasks, Ui.line);
            case BYE:
                return new ByeCmd(tasks, Ui.line);
            case FIND:
                return new FindCmd(tasks, Ui.line);
            default:
                return new UnrecognizedCmd(tasks, commandLine);
            }
    } 

}
