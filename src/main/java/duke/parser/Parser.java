package duke.parser;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;

/**
 * Encapsulates the System.in parser of <code>Duke</code>.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Parser {
    /**
    * String to register a list command.
     */
    public static final String LIST_STRING = "list";
    /**
     * String to register a mark command.
     */
    public static final String MARK_STRING = "mark";
    /**
     * String to register an unmark command.
     */
    public static final String UNMARK_STRING = "unmark";
    /**
     * String to register a todo command.
     */
    public static final String TODO_STRING = "todo";
    /**
     * String to register a deadline command.
     */
    public static final String DEADLINE_STRING = "deadline";
    /**
     * String to register an event command.
     */
    public static final String EVENT_STRING = "event";
    /**
     * String to register a delete command.
     */
    public static final String DELETE_STRING = "delete";
    /**
     * String to register a bye command.
     */
    public static final String BYE_STRING = "bye";
    /**
     * String to register a clear command.
     */
    public static final String CLEAR_STRING = "clear";
    /**
     * String to register a find command.
     */
    public static final String FIND_STRING = "find";
    /**
     * Parsers the user's input to return the respective <code>Command</code>.
     * @param fullCommand A <code>String</code> of the user's full input.
     * @return The <code>Command</code> corresponding to what the user gave.
     * @throws DukeBadInstructionFormatException if input contains '@'
     */
    public static Command parse(String fullCommand)
            throws DukeBadInstructionFormatException {
        if (fullCommand.contains(Storage.SPLITTER)) {
            throw new DukeBadInstructionFormatException("Input cannot contain @");
        }
        String[] splitted = fullCommand.split(" ");
        switch (splitted[0]) {

        case LIST_STRING:
            Command listC = new ListCommand(fullCommand);
            return listC;

        case MARK_STRING:
            Command markC = new MarkCommand(fullCommand);
            return markC;

        case UNMARK_STRING:
            Command unmarkC = new UnmarkCommand(fullCommand);
            return unmarkC;

        case TODO_STRING:
            Command todoC = new TodoCommand(fullCommand);
            return todoC;

        case DEADLINE_STRING:
            Command deadlineC = new DeadlineCommand(fullCommand);
            return deadlineC;

        case EVENT_STRING:
            Command eventC = new EventCommand(fullCommand);
            return eventC;

        case DELETE_STRING:
            Command deleteC = new DeleteCommand(fullCommand);
            return deleteC;

        case BYE_STRING:
            Command byeC = new ByeCommand(fullCommand);
            return byeC;

        case CLEAR_STRING:
            Command clearC = new ClearCommand(fullCommand);
            return clearC;

        case FIND_STRING:
            Command findC = new FindCommand(fullCommand);
            return findC;

        default:
            Command unknownC = new UnknownCommand(fullCommand);
            return unknownC;
        }

    }
}
