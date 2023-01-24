package duke;

import duke.commands.*;

/**
 * The class used to identify user commands.
 */
public class Parser {
    private enum CMD {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;
    }

    /**
     * To identify which command the user typed.
     *
     * @param cmdLine the whole line of command the user typed
     * @return a Command object that is the command the user meant to type
     */
    public static Command parse(String cmdLine) {
        try {
            CMD order = CMD.valueOf(cmdLine.split(" ")[0].toUpperCase());
            switch (order) {
                case BYE:
                    return new Bye();
                case LIST:
                    return new List();
                case MARK:
                    return new Mark(cmdLine);
                case UNMARK:
                    return new Unmark(cmdLine);
                case TODO:
                    return new CreateTodo(cmdLine);
                case DEADLINE:
                    return new CreateDeadline(cmdLine);
                case EVENT:
                    return new CreateEvent(cmdLine);
                case DELETE:
                    return new Delete(cmdLine);
            }
        } catch (IllegalArgumentException e) {
            return new notACommand();
        }
        return new notACommand();
    }
}
