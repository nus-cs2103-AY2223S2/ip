package duke;

import duke.commands.*;

/**
 * The class used to identify user commands.
 */
public class Parser {
    private enum cmds {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;
    }

    /**
     * To identify which command the user typed.
     *
     * @param cmdLine the whole line of command the user typed
     * @return a Command object that is the command the user meant to type
     */
    public static Command parse(String cmdLine) {
        try {
            cmds order = cmds.valueOf(cmdLine.split(" ")[0].toUpperCase());
            switch (order) {
            case BYE:
                return new Bye();
                // Fallthrough
            case LIST:
                return new List();
                // Fallthrough
            case MARK:
                return new Mark(cmdLine);
                // Fallthrough
            case UNMARK:
                return new Unmark(cmdLine);
                // Fallthrough
            case TODO:
                return new CreateTodo(cmdLine);
                // Fallthrough
            case DEADLINE:
                return new CreateDeadline(cmdLine);
                // Fallthrough
            case EVENT:
                return new CreateEvent(cmdLine);
                // Fallthrough
            case DELETE:
                return new Delete(cmdLine);
                // Fallthrough
            case FIND:
                return new Find(cmdLine);
                // Fallthrough
            default:
                return new notACommand();
                // Fallthrough
            }
        } catch (IllegalArgumentException e) {
            return new notACommand();
        }
    }
}
