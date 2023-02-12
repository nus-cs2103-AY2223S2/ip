package duke.parser;

import java.util.Arrays;

import duke.command.*;
import duke.exceptions.TaskException;


/**
 * Identifies command and executes its class respectively
 */
public class Parser {

    /**
     * Contains instructions to be entered by user to control system
     */
    public enum Instructions {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UPDATE
    }

    /**
     * Identifies command based on the input given by the user and executes it
     *
     * @param input user command is passed in as String
     * @return command class of type Command
     * @throws TaskException returns an exception under TaskException class
     */
    public Command parse(String input) throws TaskException {
        String[] part = input.split(" ");
        int index = 0;
        String first_word = part[0].toUpperCase();

        if (part[0].equals("mark") || part[0].equals("unmark") ||
                part[0].equals("delete") || part[0].equals("update")) {
            index = Integer.parseInt(part[1]) - 1;
        }

        if (!Arrays.stream(Instructions.values()).anyMatch(x -> x.toString().equals(first_word))) {
            return new DefaultCommand();
        }

        Instructions instruction = Instructions.valueOf(first_word);

        switch (instruction) {
        case BYE:
            return new ByeCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            return new MarkCommand(index);

        case UNMARK:
            return new UnmarkCommand(index);

        case TODO:
        case DEADLINE:
        case EVENT: {
            return new AddCommand(instruction.toString(), input);
        }

        case DELETE:
            return new DeleteCommand(index);

        case FIND:
            return new FindCommand(part[1]);

        case UPDATE:
            return new UpdateCommand(index, input);

        default:
            return new DefaultCommand();
        }
    }
}
