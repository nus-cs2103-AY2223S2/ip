package duke.parser;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.controllers.CheckoutCommand;
import duke.controllers.Command;
import duke.controllers.DateCommand;
import duke.controllers.DeadlineCommand;
import duke.controllers.DeleteCommand;
import duke.controllers.EventCommand;
import duke.controllers.FindCommand;
import duke.controllers.GoodbyeCommand;
import duke.controllers.ListCommand;
import duke.controllers.MarkCommand;
import duke.controllers.TodoCommand;
import duke.controllers.UndoCommand;
import duke.controllers.UnmarkCommand;
import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Represents a generic Parser that parses a string input.
 * It does not need to be initialized.
 */
public abstract class Parser {
    private static final Pattern VALID_COMMAND =
            Pattern.compile(
                    "^(?<cmd>list|bye|mark|date|unmark|delete|todo|deadline|event|find|undo|checkout)(?<arguments>.*)?",
                    Pattern.CASE_INSENSITIVE);
    private static final Command invalidCommand =
            new Command(CommandType.INVALID) {
                @Override
                public String execute(Supplier<? extends CacheManager> taskList) throws DukeException {
                    throw new DukeException("I'm sorry, but I don't know what that means~");
                }
            };

    private static Command parse(CommandType cmd, String rInput) {
        switch(cmd) {
        case BYE: return new GoodbyeCommand();
        case LIST: return new ListCommand(rInput);
        case MARK: return new MarkCommand(rInput);
        case DATE: return new DateCommand(rInput);
        case UNMARK: return new UnmarkCommand(rInput);
        case DELETE: return new DeleteCommand(rInput);
        case TODO: return new TodoCommand(rInput);
        case DEADLINE: return new DeadlineCommand(rInput);
        case EVENT: return new EventCommand(rInput);
        case FIND: return new FindCommand(rInput);
        case CHECKOUT: return new CheckoutCommand(rInput);
        case UNDO: return new UndoCommand(rInput);
        default: return invalidCommand;
        }
    }

    /**
     * Parses the string input and returns an appropriate command.
     *
     * @param input String user input.
     * @return Command to be executed.
     */
    public static Command parse(String input) {
        String uInput = input.toLowerCase().trim();
        Matcher matcher = VALID_COMMAND.matcher(uInput);
        if (matcher.find()) {
            String cmd = matcher.group("cmd").strip().toUpperCase();
            CommandType cmdType = CommandType.valueOf(cmd);
            return parse(cmdType, uInput);
        } else {
            return invalidCommand;
        }
    }
}
