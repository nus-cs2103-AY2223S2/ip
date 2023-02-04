package duke.parser;

import duke.Deadline;
import duke.Event;
import duke.Todo;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

public class Parser {
    public enum ValidCommands {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE
    }

    public static Command parse(String command) throws DukeException {
        String[] output = command.split(" ", 2);
        ValidCommands validCommands = ValidCommands.valueOf(output[0].toUpperCase());
        switch (validCommands) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case TODO:
                return new AddCommand(new Todo(output[1]));
            case DEADLINE:
                String[] deadlineFormatter = output[1].split(" /by ");
                if (deadlineFormatter.length < 2 ) {
                    throw new DukeException("Either the description or deadline of the task is missing");
                } else {
                    return new AddCommand(new Deadline(deadlineFormatter[0], deadlineFormatter[1]));
                }
            case EVENT:
                String[] details = output[1].split(" /", 3);
                String[] eventFormatter = output[1].split(" /from ");
                System.out.println(eventFormatter[0]);
                //System.out.println(eventFormatter[1]);
                if (details.length < 3 ) {
                    throw new DukeException("Either the description or dates (from/to) of the task is missing");
                }
                else {
                    String[] startEndDate = eventFormatter[1].split(" /to ");
                    return new AddCommand(new Event(eventFormatter[0], startEndDate[0], startEndDate[1]));
                }
            case MARK:
                return new MarkCommand(Integer.parseInt(output[1]));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(output[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(output[1]));
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
