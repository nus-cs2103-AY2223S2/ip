package dude.parser;

import dude.exception.DudeException;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.Todo;
import dude.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE
    }

    public static Command parse(String command) throws DudeException{
        String[] cmd = command.split(" ", 2);
        CommandType commandType = CommandType.valueOf(cmd[0].toUpperCase());
        String[] format;

        validate(commandType, command);

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
                return new AddCommand(new Deadline(format[0], format[1]));
            case EVENT:
                format = cmd[1].split(" /from ");
                String[] details = format[1].split(" /to ");
                return new AddCommand(new Event(format[0], details[0], details[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(cmd[1]));
        }
        return new ExitCommand();
    }

    public static void validate(CommandType commandType, String command) throws DudeException {
        String[] cmd = command.split(" ", 2);
        String[] input = command.split(" ");
        String[] format;

        switch (commandType) {
            case LIST:
            case BYE:
                if (cmd.length != 1) {
                    throw new DudeException("\tUhh... I think you added extra commands?");
                }
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                if (input.length > 2) {
                    throw new DudeException("\tUhh... I think you added extra commands leh");
                } else if (input.length < 2) {
                    throw new DudeException("\tUhh... You also need to put the task after the command");
                } else {
                    try {
                        Integer.parseInt(cmd[1]);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new DudeException("\tUhh... You need to use a valid task number also");
                    }
                }
                break;
            case TODO:
                if (cmd.length < 2) {
                    throw new DudeException("\tUhh...  You need to type the description also");
                }
                break;
            case DEADLINE:
                if (!cmd[1].contains("/by")) {
                    throw new DudeException("\tUhh...  You need to use /by to indicate the date");
                } else if (input[1].equals("/by")) {
                    throw new DudeException("\tUhh...  You need to include the description also");
                } else if (input[input.length - 1].equals("/by")) {
                    throw new DudeException("\tUhh...  You need to include the date also");
                }

                format = cmd[1].split(" /by ");
                try {
                    LocalDate.parse(format[1]);
                }  catch (DateTimeParseException e) {
                    throw new DudeException("\tUhh... You using wrong format of date leh (YYYY-MM-DD HHmm)");
                }
                break;
            case EVENT:
                if (!cmd[1].contains("/from") || !cmd[1].contains("/to")) {
                    throw new DudeException("\tUhh...  You need to use /from and /to to indicate the dates for the event");
                } else if (input[1].equals("/from") || input[1].equals("/to")) {
                    throw new DudeException("\tUhh...  You need to include the description also");
                } else if (input[input.length - 1].equals("/from")
                        || input[input.length - 1].equals("/to")) {
                    throw new DudeException("\tUhh... Please include the dates and ensure it is in the valid format");
                }

                format = cmd[1].split(" /from ");
                String[] details = format[1].split(" /to ");

                try {
                    if (LocalDate.parse(details[0]).compareTo(LocalDate.parse(details[1])) < 0) {
                        throw new DudeException("\tUhh... Your start date must be earlier than your end date");
                    }
                }  catch (DateTimeParseException e) {
                    throw new DudeException("\tUhh... You using wrong format of date leh (YYYY-MM-DD HHmm)");
                }
                break;
        }
    }
}
