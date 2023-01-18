package dude.parser;

import dude.Deadline;
import dude.Event;
import dude.Task;
import dude.Todo;
import dude.command.*;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeMissingCommandException;

import java.util.List;

public class Parser {
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE
    }

    public static Command parse(String command) {
        String[] cmd = command.split(" ", 2);
        CommandType commandType = CommandType.valueOf(cmd[0].toUpperCase());
        String[] format;
        switch (commandType) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case MARK:
                return new MarkCommand(Integer.parseInt(cmd[1]));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(cmd[1]));
            case TODO:
                return new AddCommand(new Todo(cmd[1]));
            case DEADLINE:
                format = cmd[1].split(" /by ");
//                if (format.length < 2) throw new DudeMissingCommandException();
                return new AddCommand(new Deadline(format[0], format[1]));
            case EVENT:
                format = cmd[1].split(" /from ");
//                if (format.length < 2) throw new DudeMissingCommandException();
                String[] details = format[1].split(" /to ");
//                if (details.length < 2) throw new DudeMissingCommandException();
                return new AddCommand(new Event(format[0], details[0], details[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(cmd[1]));
//            default:
//                throw new DudeInvalidCommandException();


        }
        return new ExitCommand();
    }
}
