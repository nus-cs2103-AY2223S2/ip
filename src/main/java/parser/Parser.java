package parser;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controllers.*;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

/**
 * Represents a generic Parser that parses a string input.
 * It does not need to be initialized.
 */
public abstract class Parser {
    private static final Pattern VALID_COMMAND =
            Pattern.compile("^(?<cmd>list|bye|mark|date|unmark|delete|todo|deadline|event)(?<arguments>.*)?",
                    Pattern.CASE_INSENSITIVE);
    private static final Command invalidCommand = new Command(CommandType.INVALID) {
        @Override
        public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    };

    private static Command parse(CommandType cmd, String arguments) {
        switch(cmd) {
        case BYE:        return new GoodbyeCommand();
        case LIST:       return new ListCommand();
        case MARK:       return new MarkCommand(arguments);
        case DATE:       return new DateCommand(arguments);
        case UNMARK:     return new UnmarkCommand(arguments);
        case DELETE:     return new DeleteCommand(arguments);
        case TODO:       return new TodoCommand(arguments);
        case DEADLINE:   return new DeadlineCommand(arguments);
        case EVENT:      return new EventCommand(arguments);
        default:         return invalidCommand;
        }
    }

    /**
     * Parses the string input and returns an appropriate command.
     *
     * @param input String user input.
     * @return Command to be executed.
     */
    public static Command parse(String input) {
        String uInput = input.toLowerCase();
        Matcher matcher = VALID_COMMAND.matcher(uInput);
        if (matcher.find()) {
            String cmd  = matcher.group("cmd").strip().toUpperCase();
            CommandType cmdType = CommandType.valueOf(cmd);
            return parse(cmdType, uInput);
        } else {
            return invalidCommand;
        }
    }
}
