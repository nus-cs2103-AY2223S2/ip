package duke;

import duke.commands.Bye;
import duke.commands.Command;
import duke.commands.CreateDeadline;
import duke.commands.CreateEvent;
import duke.commands.CreateTodo;
import duke.commands.Delete;
import duke.commands.Find;
import duke.commands.List;
import duke.commands.Mark;
import duke.commands.NotACommand;
import duke.commands.Unmark;

/**
 * The class used to identify user commands.
 */
public class Parser {
    private enum Order {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;
    }

    /**
     * Identifies which command the user typed.
     *
     * @param cmdLine the whole line of command the user typed
     * @return a Command object that is the command the user meant to type
     */
    public static Command parse(String cmdLine) {
        try {
            Order order = Order.valueOf(cmdLine.split(" ")[0].toUpperCase());
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
                return new NotACommand();
                // Fallthrough
            }
        } catch (IllegalArgumentException e) {
            return new NotACommand();
        }
    }
}
