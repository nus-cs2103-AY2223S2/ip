package duke;
import java.io.*;
import java.util.*;

import duke.command.*;
/**
 * helps parse the user input and return the corresponding object
 */
public class Parser {
    public static Command parseIn(String in) throws DukeException {
        String[] parmArr = in.split("\\s+");
        List<String> parm = Arrays.asList(parmArr);
        CommandEnum command = CommandEnum.fromString(parm.get(0));
        switch(command) {
        case LIST:
            return new UiCommand();
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(command, parm.subList(1, parm.size()));
        case MARK:
            return new MarkCommand(Integer.parseInt(parm.get(1)), true);
        case UNMARK:
            return new MarkCommand(Integer.parseInt(parm.get(1)), false);
        case DELETE:
            return new DeleteCommand(Integer.parseInt(parm.get(1)));
        case FIND:
            return new FindCommand(parm.subList(1, parm.size()));
        case UNDO:
            return new UndoCommand();
        default:
            throw new DukeException();
        }
    }
}
