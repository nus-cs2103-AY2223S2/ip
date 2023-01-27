package duke;

import duke.commands.*;

public class Parser {
    private enum Type {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    public static Command parse(String input) throws IllegalArgumentException{
        String[] words = input.split(" ");
        Type t = Type.valueOf(words[0].toUpperCase());
        switch(t) {
        case LIST:
            return new ListCommand(input);

        case DEADLINE:
            return new DeadlineCommand(input);

        case UNMARK:
            return new UnmarkCommand(input);

        case TODO:
            return new TodoCommand(input);

        case EVENT:
            return new EventCommand(input);

        case DELETE:
            return new DeleteCommand(input);

        case MARK:
            return new MarkCommand(input);

        case FIND:
            return new FindCommand(input);

        case BYE:
            return new ByeCommand(input);

        default:
            throw new IllegalArgumentException();
        }
    }
}
